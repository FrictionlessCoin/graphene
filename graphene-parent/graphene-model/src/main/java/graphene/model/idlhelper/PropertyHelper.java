/**
 * Copyright (c) 2013-2014 Oculus Info Inc.
 * http://www.oculusinfo.com/
 *
 * Released under the MIT License.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package graphene.model.idlhelper;

import graphene.model.idl.G_BoundedRange;
import graphene.model.idl.G_DistributionRange;
import graphene.model.idl.G_GeoData;
import graphene.model.idl.G_ListRange;
import graphene.model.idl.G_Property;
import graphene.model.idl.G_PropertyTag;
import graphene.model.idl.G_PropertyType;
import graphene.model.idl.G_Provenance;
import graphene.model.idl.G_SingletonRange;
import graphene.model.idl.G_Uncertainty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class PropertyHelper extends G_Property {

	public static PropertyHelper from(final G_Property property) {
		if (property == null) {
			return null;
		}
		if (property instanceof PropertyHelper) {
			return (PropertyHelper) property;
		}

		return new PropertyHelper(property.getKey(), property.getFriendlyText(), property.getProvenance(),
				property.getUncertainty(), property.getTags(), property.getRange());
	}

	/**
	 * This is an ugly mess since it's O(n) and we may have many properties.
	 * Also, such functionality should be available in the main object. XXX: Fix
	 * this by changing the IDL to use a map of G_Properties instead of a list.
	 * We're already treating it like a map since we assume there are only one
	 * of each key below.
	 * 
	 * @param props
	 * @param key
	 * @return
	 */
	public static G_Property getPropertyByKey(final List<G_Property> props, final String key) {
		if (props == null) {
			return null;
		}
		for (final G_Property prop : props) {
			if (prop.getKey().equalsIgnoreCase(key)) {
				return prop;
			}
		}
		return null;
	}

	public static Object getSingletonValue(final G_Property property) {
		return ((G_SingletonRange) property.getRange()).getValue();
	}

	public static Object getSingletonValueByKey(final List<G_Property> props, final String key) {
		return getSingletonValue(getPropertyByKey(props, key));

	}

	public PropertyHelper(final G_PropertyTag tag, final Date value) {
		this(tag.name(), tag.name(), value, Collections.singletonList(tag));
	}

	public PropertyHelper(final G_PropertyTag tag, final double value) {
		this(tag.name(), tag.name(), value, Collections.singletonList(tag));
	}

	public PropertyHelper(final G_PropertyTag tag, final String value) {
		this(tag.name(), tag.name(), value, Collections.singletonList(tag));
	}

	public PropertyHelper(final String key, final Object value, final G_PropertyTag tag) {
		setKey(key);
		setFriendlyText(key.replaceAll("([a-z])([A-Z0-9])", "$1 $2").replace('_', ' '));
		setProvenance(null);
		setUncertainty(null);
		setTags(new ArrayList<G_PropertyTag>(2));
		setRange(new SingletonRangeHelper(value));

		getTags().add(tag);
	}

	public PropertyHelper(final String key, final String friendlyText, final Date date, final List<G_PropertyTag> tags) {
		this(key, friendlyText, date.getTime(), G_PropertyType.DATE, tags);
	}

	public PropertyHelper(final String key, final String friendlyText, final double value,
			final List<G_PropertyTag> tags) {
		this(key, friendlyText, value, G_PropertyType.DOUBLE, tags);
	}

	public PropertyHelper(final String key, final String friendlyText, final G_GeoData value,
			final List<G_PropertyTag> tags) {
		this(key, friendlyText, value, G_PropertyType.GEO, tags);
	}

	public PropertyHelper(final String key, final String friendlyText, final G_Provenance provenance,
			final G_Uncertainty uncertainty, final List<G_PropertyTag> tags, final Object range) {
		setKey(key);
		setFriendlyText(friendlyText);
		setProvenance(provenance);
		setUncertainty(uncertainty);
		setTags(tags);
		setRange(range);
	}

	public PropertyHelper(final String key, final String friendlyText, final long value, final List<G_PropertyTag> tags) {
		this(key, friendlyText, value, G_PropertyType.LONG, tags);
	}

	public PropertyHelper(final String key, final String friendlyText, final Object value, final G_PropertyType type,
			final G_PropertyTag tag) {
		setKey(key);
		setFriendlyText(friendlyText);
		setProvenance(null);
		setUncertainty(null);
		setTags(new ArrayList<G_PropertyTag>(2));
		setRange(new SingletonRangeHelper(value, type));

		getTags().add(tag);
	}

	public PropertyHelper(final String key, final String friendlyText, final Object value, final G_PropertyType type,
			final G_Provenance provenance, final G_Uncertainty uncertainty, final List<G_PropertyTag> tags) {
		setKey(key);
		setFriendlyText(friendlyText);
		setProvenance(provenance);
		setUncertainty(uncertainty);
		setTags(tags);
		setRange(new SingletonRangeHelper(value, type));
	}

	public PropertyHelper(final String key, final String friendlyText, final Object value, final G_PropertyType type,
			final List<G_PropertyTag> tags) {
		setKey(key);
		setFriendlyText(friendlyText);
		setProvenance(null);
		setUncertainty(null);
		setTags(tags);
		setRange(new SingletonRangeHelper(value, type));
	}

	public PropertyHelper(final String key, final String friendlyText, final Object startValue, final Object endValue,
			final G_PropertyType type, final List<G_PropertyTag> tags) {
		setKey(key);
		setFriendlyText(friendlyText);
		setProvenance(null);
		setUncertainty(null);
		setTags(tags);
		setRange(G_BoundedRange.newBuilder().setStart(startValue).setEnd(endValue).setType(type));
	}

	public PropertyHelper(final String key, final String friendlyText, final String value,
			final List<G_PropertyTag> tags) {
		this(key, friendlyText, value, G_PropertyType.STRING, tags);
	}

	public G_PropertyType getType() {
		final Object range = getRange();
		if (range instanceof G_SingletonRange) {
			return ((G_SingletonRange) range).getType();
		} else if (range instanceof G_ListRange) {
			return ((G_ListRange) range).getType();
		} else if (range instanceof G_BoundedRange) {
			return ((G_BoundedRange) range).getType();
		} else if (range instanceof G_DistributionRange) {
			return ((G_DistributionRange) range).getType();
		}
		return null;
	}

	/**
	 * Look at the range, and return the value object associated with the range.
	 * 
	 * @return
	 */
	public Object getValue() {
		final Object range = getRange();
		if (range == null) {
			return null;
		}

		if (range instanceof G_SingletonRange) {
			return ((G_SingletonRange) range).getValue();
		} else if (range instanceof G_ListRange) {
			return ((G_ListRange) range).getValues().iterator().next();
		} else if (range instanceof G_BoundedRange) {
			final G_BoundedRange bounded = (G_BoundedRange) range;
			return bounded.getStart() != null ? bounded.getStart() : bounded.getEnd();
		} else if (range instanceof G_DistributionRange) {
			final G_DistributionRange dist = (G_DistributionRange) range;
			return dist.getDistribution();
		}

		return null;
	}

	public List<Object> getValues() {
		final Object range = getRange();

		if (range != null) {
			if (range instanceof G_SingletonRange) {
				return Collections.singletonList(((G_SingletonRange) range).getValue());
			} else if (range instanceof G_ListRange) {
				return ((G_ListRange) range).getValues();
			} else if (range instanceof G_BoundedRange) {
				final G_BoundedRange bounded = (G_BoundedRange) range;
				return Arrays.asList(bounded.getStart(), bounded.getEnd());
			} else if (range instanceof G_DistributionRange) {
				final G_DistributionRange dist = (G_DistributionRange) range;
				final List<Object> values = new ArrayList<Object>(dist.getDistribution().size());
				values.addAll(dist.getDistribution());
				return values;
			}
		}

		return Collections.emptyList();
	}

	public boolean hasTag(final G_PropertyTag tag) {
		return getTags().contains(tag);
	}

	public boolean hasValue() {
		return getValue() != null;
	}

}

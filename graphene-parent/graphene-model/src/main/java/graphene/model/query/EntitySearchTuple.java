package graphene.model.query;

import graphene.model.idhelper.PropertyHelper;
import graphene.model.idl.G_CanonicalPropertyType;
import graphene.model.idl.G_SearchTuple;
import graphene.model.idl.G_SearchType;

/**
 * A more specialized version of the core's Search Tuple. This allows family
 * type and search types to be specified PER query item in the query.
 * 
 * @see {@link G_SearchTuple} in the core
 * @see {@link EntitySearchTypeHelper} which parses query strings to make a list
 *      of these tuples
 * @author djue
 * 
 * @param <T>
 */
public class EntitySearchTuple<T> extends G_SearchTuple<T> {
	private G_CanonicalPropertyType family;

	/*
	 * If this value is set it will likely override any provided value for
	 * family.
	 */
	private String specificPropertyType;

	/**
	 * Public constructor for more labor intensive manual building.
	 */
	public EntitySearchTuple() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Helper constructor. Note that if this class ends up being generated by
	 * Avro, we may need to put helper constructors like this in a helper class
	 * that extends this one @see {@link PropertyHelper}
	 * 
	 * @param searchType
	 * @param family
	 * @param specificPropertyType
	 * @param value
	 */
	public EntitySearchTuple(G_SearchType searchType,
			G_CanonicalPropertyType family, String specificPropertyType, T value) {
		super();
		this.searchType = searchType;
		this.family = family;
		this.specificPropertyType = specificPropertyType;
		this.value = value;
	}

	/**
	 * Helper constructor. Note that if this class ends up being generated by
	 * Avro, we may need to put helper constructors like this in a helper class
	 * that extends this one @see {@link PropertyHelper}
	 * 
	 * @param searchType
	 * @param family
	 * @param value
	 */
	public EntitySearchTuple(G_SearchType searchType,
			G_CanonicalPropertyType family, T value) {
		this.searchType = searchType;
		this.family = family;
		this.specificPropertyType = null;
		this.value = value;
	}

	/**
	 * Helper constructor. Note that if this class ends up being generated by
	 * Avro, we may need to put helper constructors like this in a helper class
	 * that extends this one @see {@link PropertyHelper}
	 * 
	 * @param searchType
	 * @param specificPropertyType
	 * @param value
	 */
	public EntitySearchTuple(G_SearchType searchType,
			String specificPropertyType, T value) {
		this.searchType = searchType;
		this.specificPropertyType = specificPropertyType;
		this.value = value;
	}

	public G_CanonicalPropertyType getFamily() {
		return family;
	}

	public String getSpecificPropertyType() {
		return specificPropertyType;
	}

	public void setFamily(G_CanonicalPropertyType family) {
		this.family = family;
	}

	public void setSpecificPropertyType(String specificPropertyType) {
		this.specificPropertyType = specificPropertyType;
	}

	@Override
	public String toString() {
		return "EntitySearchTuple ["
				+ (family != null ? "family=" + family + ", " : "")
				+ (specificPropertyType != null ? "specificPropertyType="
						+ specificPropertyType + ", " : "")
				+ (searchType != null ? "searchType=" + searchType + ", " : "")
				+ (value != null ? "value=" + value : "") + "]";
	}

}
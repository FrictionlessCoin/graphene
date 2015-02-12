/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package graphene.model.idl;

@SuppressWarnings("all")
/**  */
@org.apache.avro.specific.AvroGenerated
public interface G_GraphBuilder {
  public static final org.apache.avro.Protocol PROTOCOL = org.apache.avro.Protocol.parse("{\"protocol\":\"G_GraphBuilder\",\"namespace\":\"graphene.model.idl\",\"doc\":\"\",\"types\":[{\"type\":\"enum\",\"name\":\"G_PropertyTag\",\"doc\":\"Tags are defined by the application layer as a taxonomy of user and application concepts,\\n\\t independent of the data sources. This allows application semantics to be re-used with new\\n\\t data, with a minimum of new software design and development. Data layer entity types, link\\n\\t types and properties should be mapped into the list of tags. The application layer must be\\n\\t able to search by native field name or by tag interchangeably, and properties returned must\\n\\t contain both native field names as well as tags.\\n\\t \\n\\t The list of tags may change as application features evolve, though that will require\\n\\t collaboration with the data layer providers. Evolving the tag list should not change the\\n\\t Data Access or Search APIs.\\n\\n\\t This is the current list of tags for Properties:\\n\\t \\n\\t CHANGED in 1.5:\\n\\t   - CREDIT/DEBIT changed to INFLOWING/OUTFLOWING\\n\\t   - added USD\\n\\t   - added DURATION\\n\\t   \\n\\t CHANGED in 1.6:\\n\\t   - added ENTITY_TYPE\\n\\t   - added ACCOUNT_OWNER, CLUSTER_SUMMARY, COUNTRY_CODE\\n\\t   \\n\\t CHANGED in 1.7:\\n\\t   - added CLUSTER\",\"symbols\":[\"ID\",\"TYPE\",\"ENTITY_TYPE\",\"ACCOUNT_OWNER\",\"CLUSTER_SUMMARY\",\"CLUSTER\",\"NAME\",\"LABEL\",\"STAT\",\"TEXT\",\"STATUS\",\"ANNOTATION\",\"WARNING\",\"LINKED_DATA\",\"IMAGE\",\"GEO\",\"COUNTRY_CODE\",\"DATE\",\"AMOUNT\",\"INFLOWING\",\"OUTFLOWING\",\"COUNT\",\"SERIES\",\"CONSTRUCTED\",\"RAW\",\"USD\",\"DURATION\"]},{\"type\":\"enum\",\"name\":\"G_EntityTag\",\"doc\":\"This is the current list of tags for Entities:\\n\\t \\n\\t CHANGED in 1.6:\\n\\t   - added ACCOUNT_OWNER, CLUSTER_SUMMARY\",\"symbols\":[\"ACCOUNT_OWNER\",\"ACCOUNT\",\"GROUP\",\"CLUSTER\",\"CLUSTER_SUMMARY\",\"FILE\",\"ANONYMOUS\",\"PROMPT_FOR_DETAILS\",\"OTHER\"]},{\"type\":\"enum\",\"name\":\"G_LinkTag\",\"doc\":\"This is the current list of tags for Links:\",\"symbols\":[\"FINANCIAL\",\"SOCIAL\",\"COMMUNICATION\",\"OTHER\"]},{\"type\":\"enum\",\"name\":\"G_PropertyType\",\"doc\":\"Allowed types for Property values.\\n\\n\\t CHANGED in 1.5\",\"symbols\":[\"DOUBLE\",\"LONG\",\"BOOLEAN\",\"STRING\",\"DATE\",\"GEO\",\"OTHER\"]},{\"type\":\"record\",\"name\":\"G_Provenance\",\"doc\":\"This is a placeholder for future modeling of provenance. It is not a required field in any service calls.\",\"fields\":[{\"name\":\"uri\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"Placeholder for now. Express provenance as a single URI.\"}]},{\"type\":\"record\",\"name\":\"G_Uncertainty\",\"doc\":\"This is a placeholder for future modeling of uncertainty. It is not a required field in any service calls.\\n\\t\\n\\tCHANGED IN 1.6\",\"fields\":[{\"name\":\"confidence\",\"type\":\"double\",\"doc\":\"Placeholder for now. Express original source confidence as a single number from 0 to 1.\",\"default\":1}]},{\"type\":\"record\",\"name\":\"G_LinkedData\",\"doc\":\"A URL and MIME type representing a pointer to text, image or other external resource.\",\"fields\":[{\"name\":\"url\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"URL of the resource\"},{\"name\":\"mimeType\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"MIME type of the resource\",\"default\":null},{\"name\":\"title\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"description of the linked resource (suitable for display to the user in an hypertext link)\",\"default\":null}]},{\"type\":\"record\",\"name\":\"G_GeoData\",\"doc\":\"Structured representation of geo-spatial data.\",\"fields\":[{\"name\":\"text\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"an address or other place reference; unstructured text field\",\"default\":null},{\"name\":\"lat\",\"type\":[\"double\",\"null\"],\"doc\":\"latitude\",\"default\":null},{\"name\":\"lon\",\"type\":[\"double\",\"null\"],\"doc\":\"longitude\",\"default\":null},{\"name\":\"cc\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"ISO 3 digit country code\",\"default\":null}]},{\"type\":\"enum\",\"name\":\"G_ContinentCode\",\"doc\":\"Standard two letter continent code\\n\\t  \\n\\t ADDED IN 1.6\",\"symbols\":[\"AF\",\"AS\",\"EU\",\"NA\",\"SA\",\"OC\",\"AN\"]},{\"type\":\"record\",\"name\":\"G_Country\",\"doc\":\"Structured representation of country data, which includes geo-spatial data.\\n\\t  \\n\\t ADDED IN 1.6\",\"fields\":[{\"name\":\"country\",\"type\":\"G_GeoData\",\"doc\":\"country geo data, including the name as text\"},{\"name\":\"region\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"the name of the global region to which the country belongs; any common classification here is acceptable\"},{\"name\":\"continent\",\"type\":\"G_ContinentCode\",\"doc\":\"continent\"}]},{\"type\":\"enum\",\"name\":\"G_DateInterval\",\"doc\":\"Temporal resolution of a duration\\n\\t \\n\\t CHANGED IN 1.5\",\"symbols\":[\"SECONDS\",\"HOURS\",\"DAYS\",\"WEEKS\",\"MONTHS\",\"QUARTERS\",\"YEARS\"]},{\"type\":\"record\",\"name\":\"G_Duration\",\"doc\":\"A temporal duration\\n\\t \\n\\t ADDED IN 1.5\",\"fields\":[{\"name\":\"interval\",\"type\":\"G_DateInterval\",\"doc\":\"time aggregation level, e.g. use monthly data\"},{\"name\":\"numIntervals\",\"type\":\"long\",\"doc\":\"number of intervals, e.g. 12 monthly intervals is a 1 year duration\"}]},{\"type\":\"record\",\"name\":\"G_DateRange\",\"doc\":\"Describes a date range at a specific resolution.\\n\\t \\n\\t CHANGED IN 1.5\",\"fields\":[{\"name\":\"startDate\",\"type\":\"long\"},{\"name\":\"numBins\",\"type\":\"long\",\"doc\":\"number of bins to return, e.g. 12 monthly bins for 1 year of data\"},{\"name\":\"durationPerBin\",\"type\":\"G_Duration\",\"doc\":\"number of intervals in a bin, e.g. 2 months/bin in 12 bins for 2 years of data\"}]},{\"type\":\"enum\",\"name\":\"G_RangeType\",\"doc\":\"Allowed types for Ranges of values.\\n\\t\\n\\tCHANGED IN 1.6\",\"symbols\":[\"SINGLETON\",\"LIST\",\"BOUNDED\",\"DISTRIBUTION\"]},{\"type\":\"record\",\"name\":\"G_SingletonRange\",\"doc\":\"Single value\\n\\t\\n\\tADDED IN 1.5\",\"fields\":[{\"name\":\"value\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"int\",\"float\",\"double\",\"long\",\"boolean\",\"G_GeoData\"]},{\"name\":\"type\",\"type\":\"G_PropertyType\",\"doc\":\"One of DOUBLE, LONG, BOOLEAN, STRING, DATE, GEO, OTHER\"}]},{\"type\":\"record\",\"name\":\"G_ListRange\",\"doc\":\"List of values\\n\\t\\n\\tADDED IN 1.5\",\"fields\":[{\"name\":\"values\",\"type\":{\"type\":\"array\",\"items\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"int\",\"float\",\"double\",\"long\",\"boolean\",\"G_GeoData\"]}},{\"name\":\"type\",\"type\":\"G_PropertyType\",\"doc\":\"One of DOUBLE, LONG, BOOLEAN, STRING, DATE, GEO, OTHER\"}]},{\"type\":\"record\",\"name\":\"G_BoundedRange\",\"doc\":\"Bounded or unbounded range values\\n\\t\\n\\tADDED IN 1.5\",\"fields\":[{\"name\":\"start\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"int\",\"float\",\"double\",\"long\",\"boolean\",\"G_GeoData\",\"null\"],\"doc\":\"start of range, or null if unbounded start\"},{\"name\":\"end\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"int\",\"float\",\"double\",\"long\",\"boolean\",\"G_GeoData\",\"null\"],\"doc\":\"end of range, or null if unbounded start\"},{\"name\":\"inclusive\",\"type\":\"boolean\",\"doc\":\"If true, range includes specified endpoint. If false, range is exclusive.\"},{\"name\":\"type\",\"type\":\"G_PropertyType\",\"doc\":\"One of DOUBLE, LONG, BOOLEAN, STRING, DATE, GEO, OTHER\"}]},{\"type\":\"record\",\"name\":\"G_Frequency\",\"doc\":\"A frequency or probability element of a distribution.\\n\\t \\n\\tADDED IN 1.6\",\"fields\":[{\"name\":\"range\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"int\",\"float\",\"double\",\"long\",\"boolean\",\"G_GeoData\",\"G_ListRange\",\"G_BoundedRange\"],\"doc\":\"the value range which occurs with some specified frequency\"},{\"name\":\"frequency\",\"type\":\"double\",\"doc\":\"frequency as a count, or probability as a value from 0-1.\"}]},{\"type\":\"record\",\"name\":\"G_DistributionRange\",\"doc\":\"Describes a distribution of values. \\n\\t \\n\\tADDED IN 1.6\",\"fields\":[{\"name\":\"distribution\",\"type\":{\"type\":\"array\",\"items\":\"G_Frequency\"}},{\"name\":\"rangeType\",\"type\":\"G_RangeType\",\"doc\":\"Describes how the values in the distribution are summarised\"},{\"name\":\"type\",\"type\":\"G_PropertyType\",\"doc\":\"The type of value that the distribution describes. One of DOUBLE, LONG, BOOLEAN, STRING, DATE, GEO, OTHER\"},{\"name\":\"isProbability\",\"type\":\"boolean\",\"doc\":\"True if a probability distribution, false if a frequency distribution\",\"default\":false}]},{\"type\":\"record\",\"name\":\"G_Property\",\"doc\":\"Each property on an Entity or Link is a name-value pair, with data type information, as well as optional\\n\\t provenance. Tags provide a way for the data provider to associate semantic annotations to each prope","rty\\n\\t in terms of the semantics of the application.  \\n\\t \\n\\t CHANGED IN 1.6\",\"fields\":[{\"name\":\"key\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"the field name in the underlying data source\"},{\"name\":\"friendlyText\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"user-friendly short-text for key (displayable)\",\"default\":null},{\"name\":\"range\",\"type\":[\"G_SingletonRange\",\"G_ListRange\",\"G_BoundedRange\",\"G_DistributionRange\"],\"doc\":\"range of values\",\"default\":null},{\"name\":\"provenance\",\"type\":[\"G_Provenance\",\"null\"],\"default\":null},{\"name\":\"uncertainty\",\"type\":[\"G_Uncertainty\",\"null\"],\"default\":null},{\"name\":\"tags\",\"type\":{\"type\":\"array\",\"items\":\"G_PropertyTag\"},\"doc\":\"one or more tags from the Tag list, used to map this source-specific field into the semantics of applications\"}]},{\"type\":\"record\",\"name\":\"G_Entity\",\"doc\":\"The nodes in the social, financial, communications or other graphs. May represent concrete individuals or organizations,\\n\\t specific proxies such as accounts, or the implicit individuals or groups behind those other entities.\",\"fields\":[{\"name\":\"uid\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"This uid must represent either (1) a globally unique identifier that can be used to retrieve data for an\\n\\t\\t\\texplicit entity, or (2) encoded query information that can be used to find a set of associated record\\n\\t\\t\\tnotionally representing an implicit entity (e.g. Loans&FirstName&LastName&Gender). Must not be used for\\n\\t\\t\\tIDs that aren't globally unique.  For example, in Kiva, \\\"Lenders\\\" has a UID (\\\"L12345\\\") while \\\"Borrowers\\\"\\n\\t\\t\\thave an encoded search in the Loans table for uid (\\\"B{loan:23456;name=Daniel}\\\").  The encoded information\\n\\t\\t\\tis data layer-specific, may be different from entity to entity or data set to data set, and should be\\n\\t\\t\\tconsidered opaque to the consumers of the entities.  Entities of type 2 should always have the Entity Tag\\n\\t\\t\\tANONYMOUS to help distinguish them when required.\"},{\"name\":\"tags\",\"type\":{\"type\":\"array\",\"items\":\"G_EntityTag\"},\"doc\":\"Entity Tags (see above, e.g. \\\"ACCOUNT\\\")\"},{\"name\":\"provenance\",\"type\":[\"G_Provenance\",\"null\"],\"default\":null},{\"name\":\"uncertainty\",\"type\":[\"G_Uncertainty\",\"null\"],\"default\":null},{\"name\":\"properties\",\"type\":{\"type\":\"array\",\"items\":\"G_Property\"}}]},{\"type\":\"record\",\"name\":\"G_Link\",\"doc\":\"* The links in the social, financial, communications or other graphs. May represent communication events, financial transactions\\n\\t * or social connections.\",\"fields\":[{\"name\":\"tags\",\"type\":{\"type\":\"array\",\"items\":\"G_LinkTag\"},\"doc\":\"Link Tags (see above, e.g. \\\"FINANCIAL\\\")\"},{\"name\":\"source\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"source entity uid\",\"default\":null},{\"name\":\"target\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"target entity uid\",\"default\":null},{\"name\":\"directed\",\"type\":\"boolean\",\"doc\":\"true if directed, false if undirected\",\"default\":true},{\"name\":\"provenance\",\"type\":[\"G_Provenance\",\"null\"],\"default\":null},{\"name\":\"uncertainty\",\"type\":[\"G_Uncertainty\",\"null\"],\"default\":null},{\"name\":\"properties\",\"type\":{\"type\":\"array\",\"items\":\"G_Property\"}}]},{\"type\":\"record\",\"name\":\"G_Cluster\",\"doc\":\"Cluster of nodes in the social, financial, communications or other graphs. \\n\\t \\n\\t CHANGED in 1.7:\\n\\t    - added version\",\"fields\":[{\"name\":\"uid\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"A unique identifier use to retrieve data about this cluster. Should not be used for non-global identifiers.\"},{\"name\":\"tags\",\"type\":{\"type\":\"array\",\"items\":\"G_EntityTag\"},\"doc\":\"Entity Tags (see DataTypes, e.g. \\\"CLUSTER\\\")\"},{\"name\":\"provenance\",\"type\":[\"G_Provenance\",\"null\"],\"default\":null},{\"name\":\"uncertainty\",\"type\":[\"G_Uncertainty\",\"null\"],\"default\":null},{\"name\":\"properties\",\"type\":{\"type\":\"array\",\"items\":\"G_Property\"}},{\"name\":\"members\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},\"doc\":\"List of id's of the members of this cluster\"},{\"name\":\"subclusters\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},\"doc\":\"List of id's of the subclusters of this cluster - empty if this is a leaf cluster in cluster hierarchy\"},{\"name\":\"parent\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"The id of the parent cluster if this is a cluster in a hierarchy - Can be null if this is a root cluster *\",\"default\":null},{\"name\":\"root\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"The id of the root cluster (top level cluster) if this is a cluster in a hierarchy - Can be null if this is a root cluster *\",\"default\":null},{\"name\":\"level\",\"type\":\"int\",\"doc\":\"The degree from the root this cluster is in the hierarchy - level = 0 if this is a root cluster *\",\"default\":0},{\"name\":\"version\",\"type\":\"int\",\"doc\":\"The version number of the cluster - each time the cluster contents is modified the version should be incremented *\",\"default\":1}]},{\"type\":\"record\",\"name\":\"G_Future\",\"doc\":\"Represents the future results of an asyncrhonous task.\\n\\t Can be passed into the FutureResults service API\",\"fields\":[{\"name\":\"uid\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"unique id of this task\"},{\"name\":\"label\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"short human-readable description of task for display\"},{\"name\":\"service\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"uid of the service task is running on\",\"default\":null},{\"name\":\"started\",\"type\":\"long\",\"doc\":\"date/time task was started\"},{\"name\":\"completed\",\"type\":\"long\",\"doc\":\"date/time task was completed (negative if not completed yet)\",\"default\":-1}]},{\"type\":\"record\",\"name\":\"G_Service\",\"doc\":\"Selectable services, returned by getServices() in various APIs\",\"fields\":[{\"name\":\"uid\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"unique id of this service\"},{\"name\":\"label\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"short human-readable description of service for display\"}]},{\"type\":\"enum\",\"name\":\"G_CanonicalPropertyType\",\"symbols\":[\"ACCOUNT\",\"ACCOUNT_CLASS\",\"ACCOUNT_TYPE\",\"REPORT_ID\",\"ADDRESS\",\"ADDRESS_CITY\",\"ADDRESS_STREET\",\"ADDRESS_BLDG\",\"ADDRESS_POSTAL_CODE\",\"ADDRESS_COUNTY\",\"ADDRESS_STATE_PROVINCE\",\"ADDRESS_COUNTRY\",\"ADDRESS_OTHER\",\"GEO\",\"EXPORTADDRESS\",\"IMPORTADDRESS\",\"ANY\",\"COMBO\",\"CONFIRMED\",\"CONTEXT\",\"CORPORATE_CUSTOMER\",\"CURRENCY\",\"CUSTOMER_CLASS\",\"CUSTOMER_NUMBER\",\"CUSTOMER_TYPE\",\"DECEASED\",\"DESCRIPTION\",\"EMAIL_ADDRESS\",\"IP\",\"DEVICE_ID\",\"SERIAL_NUMBER\",\"FAMILYROLE\",\"OTHER_ID\",\"IMPORTANCE\",\"JSONVALUE\",\"LINK\",\"MEDIA\",\"METRIC_CERTAINTY\",\"METRIC_IMPUTED\",\"METRIC_IMPUTEDFROM\",\"METRIC_PROVENANCE\",\"METRIC_SCORE\",\"NAME\",\"NODETYPE\",\"OCCUPATION\",\"OTHER\",\"PAYLOAD\",\"NGRAM\",\"PASSPORT\",\"TAXID\",\"EIN\",\"SSN\",\"GOVERNMENTID\",\"VISA\",\"LICENSEPLATE\",\"VIN\",\"FLIGHT\",\"PHONE\",\"PROVIDER_COMPANY_NAME\",\"PROVIDER_INDUSTRY\",\"SEX\",\"SYSTEMNAME\",\"TIME_CLOSING_DATE\",\"TIME_DATE\",\"TIME_DAY\",\"TIME_HOUR\",\"TIME_MONTH\",\"TIME_OPENING_DATE\",\"TIME_YEAR\",\"VALUE\",\"VIP\",\"WALLET_ADDRESS\",\"USERNAME\",\"ENTITY\",\"TYPE\",\"URL\",\"SUBJECT\",\"FREETEXT\",\"INSTITUTION\",\"FILER\",\"EVENT\",\"REDACTED\"]},{\"type\":\"enum\",\"name\":\"G_CanonicalIndustryType\",\"doc\":\"* Industry types are for classifying datasets, data sources, or individual\\n\\t * datum.\\n\\t * \\n\\t *\",\"symbols\":[\"REDACTED\",\"UNKNOWN_OR_UNSURE\",\"BANKING\",\"FINANCIAL_SERVICES\",\"FOOD_RESTAURANT\",\"COMMODITY_PROVIDER\",\"ISP\",\"TELCO\",\"TELEVISION\",\"EMAIL_PROVIDER\",\"MULTI\",\"TRANSPORT\",\"LOGISTICS\",\"ADVERTISEMENT_AGENCY\",\"EMPLOYMENT_AGENCY\",\"GOVERNMENT_AGENCY\",\"INTERNATIONAL_AGENCY\",\"NEWS_AGENCY\",\"POSTAL\",\"REAL_ESTATE\",\"EDUCATION\",\"HEALTHCARE\",\"CHARITY\"]},{\"type\":\"enum\",\"name\":\"G_CanonicalRelationshipType\",\"doc\":\"* The 21st century meaning, \\\"conceal from unauthorized\\n\\t\\t\\t\\t\\t\\t\\t * view; censor but do not destroy\\\"\",\"symbols\":[\"ISA\",\"SUPERSEDES\",\"HAS_ID\",\"MAY_BE_THE_SAME\",\"RELATED_TO_ID\",\"HAS_GLOBAL_ID\",\"OTHER_RELATION\",\"STARTED_ON\",\"ENDED_ON\",\"BORN_ON\",\"DIED_ON\",\"IN_REPORT\",\"IN_DOCUMENT\",\"IN_EVENT\",\"CREATOR_OF\",\"FRIEND_OF\",\"ENEMY_OF\",\"LIKES\",\"LOVES\",\"HATES\",\"KNOWS\",\"FAMILIAL_TIE\",\"KIN_OF\",\"GUARDIAN_OF\",\"FATHER_OF\",\"SON_OF\",\"MOTHER_OF\",\"DAUGHTER_OF\",\"BROTHER_","OF\",\"SISTER_OF\",\"COUSIN_OF\",\"WIFE_OF\",\"HUSBAND_OF\",\"MARRIED_TO\",\"SKILL_OF\",\"PERSONAL_FACTOR_OF\",\"ATTRIBUTE_OF\",\"COMMUNICATION_ID_OF\",\"DEVICE_OF\",\"PROPER_NAME_OF\",\"USERNAME_OF\",\"ALIAS_OF\",\"ADDRESS_OF\",\"GEOLOCATION_OF\",\"CONTAINED_IN\",\"GOVERNMENT_ID_OF\",\"MEMBER_OF\",\"PART_OF\",\"COMMERCIAL_ID_OF\",\"HAS_PATRONAGE\",\"SELLER_OF\",\"BUYER_OF\",\"TRANSACTION\",\"PHYSICAL_PROPERTY_OF\",\"BIOLOGICAL_PROPERTY_OF\",\"GENETIC_TRAIT_OF\",\"PRECURSOR_OF\",\"DECAY_PRODUCT_OF\",\"ISOTOPE_OF\",\"USED_IN\",\"EFFECT_OF\",\"SENT\",\"RECEIVED\",\"PLACED_CALL\",\"RECEIVED_CALL\",\"SENT_MAIL\",\"EMAIL_FORWARD_OF\",\"EMAIL_REPLY_TO\",\"SENT_EMAIL\",\"EMAIL_TO\",\"EMAIL_BCC\",\"EMAIL_CC\",\"WORKED_ON\",\"WORKS_FOR\",\"EMPLOYEE_OF\",\"OWNER_OF\",\"INTERESTED_IN\",\"PARTICIPATED_IN\",\"WORKED_WITH\",\"MANAGES\",\"CONTROLS\",\"PEER_OF\",\"POSITIVELY_BENEFITS\",\"NEUTRALLY_BENEFITS\",\"NEGATIVELY_BENEFITS\",\"REDACTED\"]},{\"type\":\"enum\",\"name\":\"G_CanonicalTruthValues\",\"symbols\":[\"TRUE\",\"FALSE\",\"UNKNOWN\",\"UNCERTAIN\",\"REDACTED\"]},{\"type\":\"enum\",\"name\":\"G_VisualTag\",\"doc\":\"* The 21st century meaning, \\\"conceal from unauthorized\\n\\t\\t\\t\\t\\t\\t * view; censor but do not destroy\\\"\",\"symbols\":[\"CHOROPLETH\",\"CARTOGRAM\",\"DOT_DISTRIBUTION\",\"PROPORTIONAL_SYMBOL\",\"CONTOUR_MAP\",\"DASYMETRIC\",\"FLOW_MAP\",\"SOM\",\"TIME_LINE\",\"TIME_SERIES\",\"GANTT\",\"STREAM_GRAPH\",\"ARC_DIAGRAM\",\"POLAR_AREA\",\"SANKEY\",\"ALLUVIAL\",\"PIE_CHART\",\"HISTOGRAM\",\"TAG_CLOUD\",\"BUBBLE_CHART\",\"BAR_CHART\",\"TREE_MAP\",\"SCATTER_PLOT\",\"LINE_CHART\",\"STEP_CHART\",\"AREA_CHART\",\"HEAT_MAP\",\"SPIDER_CHART\",\"CANDLESTICK\",\"TREE_REGULAR\",\"TREE_RADIAL\",\"TREE_HYPERBOLIC\",\"RADIAL_HIERARCHY\",\"MATRIX\",\"NODE_LINK_DIAGRAM\",\"NODE_LINK_DIAGRAM3D\",\"CIRCULAR_HIERARCHY\",\"HIVE_PLOT\",\"SUBWAY_MAP\",\"DEFAULT\",\"HIDDEN\",\"IMAGE\",\"VIDEO\",\"INFINITE_SCROLL\",\"LIST\",\"PLUGIN\",\"REDACTED\"]},{\"type\":\"record\",\"name\":\"G_IdType\",\"fields\":[{\"name\":\"name\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"friendlyName\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"index\",\"type\":\"long\"},{\"name\":\"shortName\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"tableSource\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]},{\"type\":\"record\",\"name\":\"G_PropertyKey\",\"fields\":[{\"name\":\"type\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"name\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"friendlyName\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"index\",\"type\":\"long\"}]},{\"type\":\"record\",\"name\":\"G_EdgeType\",\"fields\":[{\"name\":\"type\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"name\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"friendlyName\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"index\",\"type\":\"long\"}]},{\"type\":\"record\",\"name\":\"G_AppInfo\",\"doc\":\"* Selectable services, returned by getAppInfo() in various meta APIs\",\"fields\":[{\"name\":\"appName\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"unique id of this application\"},{\"name\":\"appVersion\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"version of this application\"},{\"name\":\"serverTime\",\"type\":\"long\",\"doc\":\"version of this application\"}]},{\"type\":\"record\",\"name\":\"G_Graph\",\"doc\":\"* a Graph object\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"* The id of the graph\"},{\"name\":\"nodes\",\"type\":{\"type\":\"array\",\"items\":\"G_Entity\"}},{\"name\":\"edges\",\"type\":{\"type\":\"array\",\"items\":\"G_Link\"}},{\"name\":\"properties\",\"type\":{\"type\":\"array\",\"items\":\"G_Property\"}}]}],\"messages\":{\"makeGraphResponse\":{\"request\":[{\"name\":\"type\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"values\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}},{\"name\":\"maxDegree\",\"type\":\"int\"},{\"name\":\"nodeType\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}],\"response\":\"G_Graph\"}}}");
  graphene.model.idl.G_Graph makeGraphResponse(java.lang.String type, java.util.List<java.lang.String> values, int maxDegree, java.lang.String nodeType) throws org.apache.avro.AvroRemoteException;

  @SuppressWarnings("all")
  /**  */
  public interface Callback extends G_GraphBuilder {
    public static final org.apache.avro.Protocol PROTOCOL = graphene.model.idl.G_GraphBuilder.PROTOCOL;
    void makeGraphResponse(java.lang.String type, java.util.List<java.lang.String> values, int maxDegree, java.lang.String nodeType, org.apache.avro.ipc.Callback<graphene.model.idl.G_Graph> callback) throws java.io.IOException;
  }
}
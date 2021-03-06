/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package graphene.model.idl;

@SuppressWarnings("all")
/**  */
@org.apache.avro.specific.AvroGenerated
public interface G_DataAccess {
  public static final org.apache.avro.Protocol PROTOCOL = org.apache.avro.Protocol.parse("{\"protocol\":\"G_DataAccess\",\"namespace\":\"graphene.model.idl\",\"doc\":\"\",\"types\":[{\"type\":\"enum\",\"name\":\"G_PropertyTag\",\"doc\":\"Tags are defined by the application layer as a taxonomy of user and application concepts,\\r\\n\\t independent of the data sources. This allows application semantics to be re-used with new\\r\\n\\t data, with a minimum of new software design and development. Data layer entity types, link\\r\\n\\t types and properties should be mapped into the list of tags. The application layer must be\\r\\n\\t able to search by native field name or by tag interchangeably, and properties returned must\\r\\n\\t contain both native field names as well as tags.\\r\\n\\t \\r\\n\\t The list of tags may change as application features evolve, though that will require\\r\\n\\t collaboration with the data layer providers. Evolving the tag list should not change the\\r\\n\\t Data Access or Search APIs.\\r\\n\\r\\n\\t This is the current list of tags for Properties:\\r\\n\\t \\r\\n\\t CHANGED in 1.5:\\r\\n\\t   - CREDIT/DEBIT changed to INFLOWING/OUTFLOWING\\r\\n\\t   - added USD\\r\\n\\t   - added DURATION\\r\\n\\t   \\r\\n\\t CHANGED in 1.6:\\r\\n\\t   - added ENTITY_TYPE\\r\\n\\t   - added ACCOUNT_OWNER, CLUSTER_SUMMARY, COUNTRY_CODE\\r\\n\\t   \\r\\n\\t CHANGED in 1.7:\\r\\n\\t   - added CLUSTER\",\"symbols\":[\"ID\",\"TYPE\",\"ENTITY_TYPE\",\"ACCOUNT_OWNER\",\"CLUSTER_SUMMARY\",\"CLUSTER\",\"NAME\",\"LABEL\",\"STAT\",\"TEXT\",\"STATUS\",\"ANNOTATION\",\"WARNING\",\"LINKED_DATA\",\"IMAGE\",\"GEO\",\"COUNTRY_CODE\",\"DATE\",\"AMOUNT\",\"INFLOWING\",\"OUTFLOWING\",\"COUNT\",\"SERIES\",\"CONSTRUCTED\",\"RAW\",\"USD\",\"DURATION\"]},{\"type\":\"enum\",\"name\":\"G_EntityTag\",\"doc\":\"This is the current list of tags for Entities:\\r\\n\\t \\r\\n\\t CHANGED in 1.6:\\r\\n\\t   - added ACCOUNT_OWNER, CLUSTER_SUMMARY\",\"symbols\":[\"ACCOUNT_OWNER\",\"ACCOUNT\",\"GROUP\",\"CLUSTER\",\"CLUSTER_SUMMARY\",\"FILE\",\"ANONYMOUS\",\"PROMPT_FOR_DETAILS\",\"OTHER\"]},{\"type\":\"enum\",\"name\":\"G_LinkTag\",\"doc\":\"This is the current list of tags for Links:\",\"symbols\":[\"FINANCIAL\",\"SOCIAL\",\"COMMUNICATION\",\"OTHER\"]},{\"type\":\"enum\",\"name\":\"G_PropertyType\",\"doc\":\"Allowed types for Property values.\\r\\n\\r\\n\\t CHANGED in 1.5\",\"symbols\":[\"DOUBLE\",\"LONG\",\"BOOLEAN\",\"STRING\",\"DATE\",\"GEO\",\"OTHER\"]},{\"type\":\"record\",\"name\":\"G_Provenance\",\"doc\":\"This is a placeholder for future modeling of provenance. It is not a required field in any service calls.\",\"fields\":[{\"name\":\"uri\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"Placeholder for now. Express provenance as a single URI.\"}]},{\"type\":\"record\",\"name\":\"G_Uncertainty\",\"doc\":\"This is a placeholder for future modeling of uncertainty. It is not a required field in any service calls.\\r\\n\\t\\r\\n\\tCHANGED IN 1.6\",\"fields\":[{\"name\":\"confidence\",\"type\":\"double\",\"doc\":\"Placeholder for now. Express original source confidence as a single number from 0 to 1.\",\"default\":1}]},{\"type\":\"record\",\"name\":\"G_LinkedData\",\"doc\":\"A URL and MIME type representing a pointer to text, image or other external resource.\",\"fields\":[{\"name\":\"url\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"URL of the resource\"},{\"name\":\"mimeType\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"MIME type of the resource\",\"default\":null},{\"name\":\"title\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"description of the linked resource (suitable for display to the user in an hypertext link)\",\"default\":null}]},{\"type\":\"record\",\"name\":\"G_GeoData\",\"doc\":\"Structured representation of geo-spatial data.\",\"fields\":[{\"name\":\"text\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"an address or other place reference; unstructured text field\",\"default\":null},{\"name\":\"lat\",\"type\":[\"double\",\"null\"],\"doc\":\"latitude\",\"default\":null},{\"name\":\"lon\",\"type\":[\"double\",\"null\"],\"doc\":\"longitude\",\"default\":null},{\"name\":\"cc\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"ISO 3 digit country code\",\"default\":null}]},{\"type\":\"enum\",\"name\":\"G_ContinentCode\",\"doc\":\"Standard two letter continent code\\r\\n\\t  \\r\\n\\t ADDED IN 1.6\",\"symbols\":[\"AF\",\"AS\",\"EU\",\"NA\",\"SA\",\"OC\",\"AN\"]},{\"type\":\"record\",\"name\":\"G_Country\",\"doc\":\"Structured representation of country data, which includes geo-spatial data.\\r\\n\\t  \\r\\n\\t ADDED IN 1.6\",\"fields\":[{\"name\":\"country\",\"type\":\"G_GeoData\",\"doc\":\"country geo data, including the name as text\"},{\"name\":\"region\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"the name of the global region to which the country belongs; any common classification here is acceptable\"},{\"name\":\"continent\",\"type\":\"G_ContinentCode\",\"doc\":\"continent\"}]},{\"type\":\"enum\",\"name\":\"G_DateInterval\",\"doc\":\"Temporal resolution of a duration\\r\\n\\t \\r\\n\\t CHANGED IN 1.5\",\"symbols\":[\"SECONDS\",\"HOURS\",\"DAYS\",\"WEEKS\",\"MONTHS\",\"QUARTERS\",\"YEARS\"]},{\"type\":\"record\",\"name\":\"G_Duration\",\"doc\":\"A temporal duration\\r\\n\\t \\r\\n\\t ADDED IN 1.5\",\"fields\":[{\"name\":\"interval\",\"type\":\"G_DateInterval\",\"doc\":\"time aggregation level, e.g. use monthly data\"},{\"name\":\"numIntervals\",\"type\":\"long\",\"doc\":\"number of intervals, e.g. 12 monthly intervals is a 1 year duration\"}]},{\"type\":\"record\",\"name\":\"G_DateRange\",\"doc\":\"Describes a date range at a specific resolution.\\r\\n\\t \\r\\n\\t CHANGED IN 1.5\",\"fields\":[{\"name\":\"startDate\",\"type\":\"long\"},{\"name\":\"numBins\",\"type\":\"long\",\"doc\":\"number of bins to return, e.g. 12 monthly bins for 1 year of data\"},{\"name\":\"durationPerBin\",\"type\":\"G_Duration\",\"doc\":\"number of intervals in a bin, e.g. 2 months/bin in 12 bins for 2 years of data\"}]},{\"type\":\"enum\",\"name\":\"G_RangeType\",\"doc\":\"Allowed types for Ranges of values.\\r\\n\\t\\r\\n\\tCHANGED IN 1.6\",\"symbols\":[\"SINGLETON\",\"LIST\",\"BOUNDED\",\"DISTRIBUTION\"]},{\"type\":\"record\",\"name\":\"G_SingletonRange\",\"doc\":\"Single value\\r\\n\\t\\r\\n\\tADDED IN 1.5\",\"fields\":[{\"name\":\"value\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"int\",\"float\",\"double\",\"long\",\"boolean\",\"G_GeoData\"]},{\"name\":\"type\",\"type\":\"G_PropertyType\",\"doc\":\"One of DOUBLE, LONG, BOOLEAN, STRING, DATE, GEO, OTHER\"}]},{\"type\":\"record\",\"name\":\"G_ListRange\",\"doc\":\"List of values\\r\\n\\t\\r\\n\\tADDED IN 1.5\",\"fields\":[{\"name\":\"values\",\"type\":{\"type\":\"array\",\"items\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"int\",\"float\",\"double\",\"long\",\"boolean\",\"G_GeoData\"]}},{\"name\":\"type\",\"type\":\"G_PropertyType\",\"doc\":\"One of DOUBLE, LONG, BOOLEAN, STRING, DATE, GEO, OTHER\"}]},{\"type\":\"record\",\"name\":\"G_BoundedRange\",\"doc\":\"Bounded or unbounded range values\\r\\n\\t\\r\\n\\tADDED IN 1.5\",\"fields\":[{\"name\":\"start\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"int\",\"float\",\"double\",\"long\",\"boolean\",\"G_GeoData\",\"null\"],\"doc\":\"start of range, or null if unbounded start\"},{\"name\":\"end\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"int\",\"float\",\"double\",\"long\",\"boolean\",\"G_GeoData\",\"null\"],\"doc\":\"end of range, or null if unbounded start\"},{\"name\":\"inclusive\",\"type\":\"boolean\",\"doc\":\"If true, range includes specified endpoint. If false, range is exclusive.\"},{\"name\":\"type\",\"type\":\"G_PropertyType\",\"doc\":\"One of DOUBLE, LONG, BOOLEAN, STRING, DATE, GEO, OTHER\"}]},{\"type\":\"record\",\"name\":\"G_Frequency\",\"doc\":\"A frequency or probability element of a distribution.\\r\\n\\t \\r\\n\\tADDED IN 1.6\",\"fields\":[{\"name\":\"range\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"int\",\"float\",\"double\",\"long\",\"boolean\",\"G_GeoData\",\"G_ListRange\",\"G_BoundedRange\"],\"doc\":\"the value range which occurs with some specified frequency\"},{\"name\":\"frequency\",\"type\":\"double\",\"doc\":\"frequency as a count, or probability as a value from 0-1.\"}]},{\"type\":\"record\",\"name\":\"G_DistributionRange\",\"doc\":\"Describes a distribution of values. \\r\\n\\t \\r\\n\\tADDED IN 1.6\",\"fields\":[{\"name\":\"distribution\",\"type\":{\"type\":\"array\",\"items\":\"G_Frequency\"}},{\"name\":\"rangeType\",\"type\":\"G_RangeType\",\"doc\":\"Describes how the values in the distribution are summarised\"},{\"name\":\"type\",\"type\":\"G_PropertyType\",\"doc\":\"The type of value that the distribution describes. One of DOUBLE, LONG, BOOLEAN, STRING, DATE, GEO, OTHER\"},{\"name\":\"isProbability\",\"type\":\"boolean\",\"doc\":\"True if a probability distribution, false if a frequency distribution\",\"default\":false}]},{\"type\":\"record\",\"name\":\"G_Property\",\"doc\":\"Each property on an Entity or Link is a name-value pair, with data type information, as well as optional\\r\\","n\\t provenance. Tags provide a way for the data provider to associate semantic annotations to each property\\r\\n\\t in terms of the semantics of the application.  \\r\\n\\t \\r\\n\\t CHANGED IN 1.6\",\"fields\":[{\"name\":\"key\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"the field name in the underlying data source\"},{\"name\":\"friendlyText\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"user-friendly short-text for key (displayable)\",\"default\":null},{\"name\":\"range\",\"type\":[\"G_SingletonRange\",\"G_ListRange\",\"G_BoundedRange\",\"G_DistributionRange\"],\"doc\":\"range of values\",\"default\":null},{\"name\":\"provenance\",\"type\":[\"G_Provenance\",\"null\"],\"default\":null},{\"name\":\"uncertainty\",\"type\":[\"G_Uncertainty\",\"null\"],\"default\":null},{\"name\":\"tags\",\"type\":{\"type\":\"array\",\"items\":\"G_PropertyTag\"},\"doc\":\"one or more tags from the Tag list, used to map this source-specific field into the semantics of applications\"}]},{\"type\":\"record\",\"name\":\"G_Entity\",\"doc\":\"The nodes in the social, financial, communications or other graphs. May represent concrete individuals or organizations,\\r\\n\\t specific proxies such as accounts, or the implicit individuals or groups behind those other entities.\",\"fields\":[{\"name\":\"uid\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"This uid must represent either (1) a globally unique identifier that can be used to retrieve data for an\\r\\n\\t\\t\\texplicit entity, or (2) encoded query information that can be used to find a set of associated record\\r\\n\\t\\t\\tnotionally representing an implicit entity (e.g. Loans&FirstName&LastName&Gender). Must not be used for\\r\\n\\t\\t\\tIDs that aren't globally unique.  For example, in Kiva, \\\"Lenders\\\" has a UID (\\\"L12345\\\") while \\\"Borrowers\\\"\\r\\n\\t\\t\\thave an encoded search in the Loans table for uid (\\\"B{loan:23456;name=Daniel}\\\").  The encoded information\\r\\n\\t\\t\\tis data layer-specific, may be different from entity to entity or data set to data set, and should be\\r\\n\\t\\t\\tconsidered opaque to the consumers of the entities.  Entities of type 2 should always have the Entity Tag\\r\\n\\t\\t\\tANONYMOUS to help distinguish them when required.\"},{\"name\":\"tags\",\"type\":{\"type\":\"array\",\"items\":\"G_EntityTag\"},\"doc\":\"Entity Tags (see above, e.g. \\\"ACCOUNT\\\")\"},{\"name\":\"provenance\",\"type\":[\"G_Provenance\",\"null\"],\"default\":null},{\"name\":\"uncertainty\",\"type\":[\"G_Uncertainty\",\"null\"],\"default\":null},{\"name\":\"properties\",\"type\":{\"type\":\"array\",\"items\":\"G_Property\"}}]},{\"type\":\"record\",\"name\":\"G_Link\",\"doc\":\"* The links in the social, financial, communications or other graphs. May represent communication events, financial transactions\\r\\n\\t * or social connections.\",\"fields\":[{\"name\":\"tags\",\"type\":{\"type\":\"array\",\"items\":\"G_LinkTag\"},\"doc\":\"Link Tags (see above, e.g. \\\"FINANCIAL\\\")\"},{\"name\":\"source\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"source entity uid\",\"default\":null},{\"name\":\"target\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"target entity uid\",\"default\":null},{\"name\":\"directed\",\"type\":\"boolean\",\"doc\":\"true if directed, false if undirected\",\"default\":true},{\"name\":\"provenance\",\"type\":[\"G_Provenance\",\"null\"],\"default\":null},{\"name\":\"uncertainty\",\"type\":[\"G_Uncertainty\",\"null\"],\"default\":null},{\"name\":\"properties\",\"type\":{\"type\":\"array\",\"items\":\"G_Property\"}}]},{\"type\":\"record\",\"name\":\"G_Cluster\",\"doc\":\"Cluster of nodes in the social, financial, communications or other graphs. \\r\\n\\t \\r\\n\\t CHANGED in 1.7:\\r\\n\\t    - added version\",\"fields\":[{\"name\":\"uid\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"A unique identifier use to retrieve data about this cluster. Should not be used for non-global identifiers.\"},{\"name\":\"tags\",\"type\":{\"type\":\"array\",\"items\":\"G_EntityTag\"},\"doc\":\"Entity Tags (see DataTypes, e.g. \\\"CLUSTER\\\")\"},{\"name\":\"provenance\",\"type\":[\"G_Provenance\",\"null\"],\"default\":null},{\"name\":\"uncertainty\",\"type\":[\"G_Uncertainty\",\"null\"],\"default\":null},{\"name\":\"properties\",\"type\":{\"type\":\"array\",\"items\":\"G_Property\"}},{\"name\":\"members\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},\"doc\":\"List of id's of the members of this cluster\"},{\"name\":\"subclusters\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},\"doc\":\"List of id's of the subclusters of this cluster - empty if this is a leaf cluster in cluster hierarchy\"},{\"name\":\"parent\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"The id of the parent cluster if this is a cluster in a hierarchy - Can be null if this is a root cluster *\",\"default\":null},{\"name\":\"root\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"The id of the root cluster (top level cluster) if this is a cluster in a hierarchy - Can be null if this is a root cluster *\",\"default\":null},{\"name\":\"level\",\"type\":\"int\",\"doc\":\"The degree from the root this cluster is in the hierarchy - level = 0 if this is a root cluster *\",\"default\":0},{\"name\":\"version\",\"type\":\"int\",\"doc\":\"The version number of the cluster - each time the cluster contents is modified the version should be incremented *\",\"default\":1}]},{\"type\":\"record\",\"name\":\"G_Future\",\"doc\":\"Represents the future results of an asyncrhonous task.\\r\\n\\t Can be passed into the FutureResults service API\",\"fields\":[{\"name\":\"uid\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"unique id of this task\"},{\"name\":\"label\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"short human-readable description of task for display\"},{\"name\":\"service\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"uid of the service task is running on\",\"default\":null},{\"name\":\"started\",\"type\":\"long\",\"doc\":\"date/time task was started\"},{\"name\":\"completed\",\"type\":\"long\",\"doc\":\"date/time task was completed (negative if not completed yet)\",\"default\":-1}]},{\"type\":\"record\",\"name\":\"G_Service\",\"doc\":\"Selectable services, returned by getServices() in various APIs\",\"fields\":[{\"name\":\"uid\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"unique id of this service\"},{\"name\":\"label\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"short human-readable description of service for display\"}]},{\"type\":\"enum\",\"name\":\"G_SortBy\",\"doc\":\"Sort type for transactions\",\"symbols\":[\"DATE\",\"AMOUNT\"]},{\"type\":\"enum\",\"name\":\"G_DirectionFilter\",\"doc\":\"Direction of desired links\",\"symbols\":[\"SOURCE\",\"DESTINATION\",\"BOTH\"]},{\"type\":\"enum\",\"name\":\"G_LinkEntityTypeFilter\",\"doc\":\"Type of entity associated with desired links\\r\\n\\t \\r\\n\\t ADDED IN 1.6\",\"symbols\":[\"ACCOUNT_OWNER\",\"ACCOUNT\",\"CLUSTER_SUMMARY\",\"ANY\"]},{\"type\":\"enum\",\"name\":\"G_LevelOfDetail\",\"doc\":\"Amount of detail requested\\r\\n\\t \\r\\n\\t ADDED IN 1.6\",\"symbols\":[\"SUMMARY\",\"FULL\"]},{\"type\":\"record\",\"name\":\"G_TransactionResults\",\"doc\":\"The set of transactions from a single call to getAllTransactions.\\r\\n\\t\\t  \\r\\n\\t ADDED IN 1.6\",\"fields\":[{\"name\":\"total\",\"type\":\"long\",\"doc\":\"total number of results FOUND, which may be more than the number returned.\",\"default\":0},{\"name\":\"results\",\"type\":{\"type\":\"array\",\"items\":\"G_Link\"},\"doc\":\"an array of transactions\"}]}],\"messages\":{\"getEntities\":{\"doc\":\"Returns a set of entities by uid. Typically requested with a summary level of detail,\\r\\n\\t appropriate for card display and clustering. Full details are requested for \\r\\n\\t detailed display views.\\r\\n\\t \\r\\n\\t Service URL: /entity/list\\r\\n\\t HTTP Method: POST and GET\\r\\n\\t \\r\\n\\t @param entities \\tuids of entities to retrieve\\r\\n\\t @return \\t\\t\\tentities\",\"request\":[{\"name\":\"entities\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}},{\"name\":\"levelOfDetail\",\"type\":\"G_LevelOfDetail\"}],\"response\":{\"type\":\"array\",\"items\":\"G_Entity\"}},\"getAccounts\":{\"doc\":\"Returns a set of accounts for each given entity. Typically requested with a summary level of detail,\\r\\n\\t appropriate for card display and cluster summarisation. Full details are requested for \\r\\n\\t detailed display views.\\r\\n\\t \\r\\n\\t Service URL: /entity/accounts\\r\\n\\t HTTP Method: POST and GET\\r\\n\\t \\r\\n\\t @param entities \\t\\tuids of entities to retrieve\\r\\n\\t @return \\t\\t\\t\\tmap of input entity IDs to an array of the accounts ","for that entity\",\"request\":[{\"name\":\"entities\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}}],\"response\":{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":\"G_Entity\"},\"avro.java.string\":\"String\"}},\"getFlowAggregation\":{\"doc\":\"Returns links to other entities related to specify entities. Each Link returned should\\r\\n\\t represent multiple records, used for displaying flow.\\r\\n\\t \\r\\n\\t CHANGED IN 1.6\\r\\n\\t   - Added entityType filter\\r\\n\\t \\r\\n\\t See the specification for the Flow Data View.\\r\\n\\t See the specification for the Time Series Data View.\\r\\n\\t \\r\\n\\t Service URL: /link/flow\\r\\n\\t HTTP Method: POST and GET\\r\\n\\t \\r\\n\\t @param entities \\t\\tuids of entities to retrieve links for\\r\\n\\t @param focusEntities \\tuids of focus entities (may be null)\\r\\n\\t @param direction \\t\\tone of: SOURCE, DESTINATION, BOTH\\r\\n\\t @param entityType\\t\\tone of: ACCOUNT_OWNER, ACCOUNT, CLUSTER_SUMMARY, ANY\\r\\n\\t @param tag \\t\\t\\tone of: FINANCIAL, SOCIAL, COMMUNICATION\\r\\n\\t @param date \\t\\t\\tin the given date range\\r\\n\\t @return \\t\\t\\t\\tmap of input entity IDs to an array of the links for that entity\",\"request\":[{\"name\":\"entities\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}},{\"name\":\"focusEntities\",\"type\":[{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},\"null\"]},{\"name\":\"direction\",\"type\":\"G_DirectionFilter\"},{\"name\":\"entityType\",\"type\":\"G_LinkEntityTypeFilter\"},{\"name\":\"tag\",\"type\":[\"G_LinkTag\",\"null\"]},{\"name\":\"date\",\"type\":[\"G_DateRange\",\"null\"]}],\"response\":{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":\"G_Link\"},\"avro.java.string\":\"String\"}},\"getTimeSeriesAggregation\":{\"doc\":\"Returns links to other entities related to specify entities.  Each Link returned should\\r\\n\\t represent multiple records, used for displaying time series.\\r\\n\\t \\r\\n\\t See the specification for the Time Series Data View.\\r\\n\\t \\r\\n\\t Service URL: /link/series\\r\\n\\t HTTP Method: POST and GET\\r\\n\\t \\r\\n\\t @param entities \\t\\tuids of entities to retrieve links for\\r\\n\\t @param focusEntities \\tuids of focus entities (may be null)\\r\\n\\t @param tag \\t\\t\\tone of: FINANCIAL, SOCIAL, COMMUNICATION\\r\\n\\t @param date \\t\\t\\tin the given date range\\r\\n\\t @return \\t\\t\\t\\tmap of input entity IDs to an array of the links for that entity\",\"request\":[{\"name\":\"entities\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}},{\"name\":\"focusEntities\",\"type\":[{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},\"null\"]},{\"name\":\"tag\",\"type\":[\"G_LinkTag\",\"null\"]},{\"name\":\"date\",\"type\":\"G_DateRange\"}],\"response\":{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":\"G_Link\"},\"avro.java.string\":\"String\"}},\"getAllTransactions\":{\"doc\":\"Returns links to other entities related to specify entities. Each link returned should\\r\\n\\t represent one raw transaction.  For financial data, these transactions will form a ledger.\\r\\n\\t For communications or social data, these are un-aggregated links for drill down by the user.\\r\\n\\t \\r\\n\\t CHANGED IN 1.6\\r\\n\\t \\t- return value type changed\\r\\n\\t \\t- added start index\\r\\n\\t \\r\\n\\t Service URL: /link/transactions\\r\\n\\t HTTP Method: POST and GET\\r\\n\\t \\r\\n\\t @param entities \\t\\tuids of entities to retrieve links for\\r\\n\\t @param direction \\t\\tone of: SOURCE, DESTINATION, BOTH\\r\\n\\t @param tag \\t\\t\\tone of: FINANCIAL, SOCIAL, COMMUNICATION\\r\\n\\t @param date \\t\\t\\tin the given date range\\r\\n\\t @param sort\\t\\t\\tSort order for transactions\\r\\n\\t @param linkFilter\\t\\tIf provided, only return transactions where the \\\"other side\\\" is in this list\\r\\n\\t @param start\\t\\t\\t0-based index of first result to return\\r\\n\\t @param max\\t\\t\\t\\tTrim the number of results this this value.\\r\\n\\t @return \\t\\t\\t\\ttransactions for the set of entities\",\"request\":[{\"name\":\"entities\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}},{\"name\":\"tag\",\"type\":[\"G_LinkTag\",\"null\"]},{\"name\":\"date\",\"type\":[\"G_DateRange\",\"null\"]},{\"name\":\"sort\",\"type\":[\"G_SortBy\",\"null\"]},{\"name\":\"linkFilter\",\"type\":[{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},\"null\"]},{\"name\":\"start\",\"type\":\"long\",\"default\":0},{\"name\":\"max\",\"type\":\"long\",\"default\":-1}],\"response\":\"G_TransactionResults\"}}}");
  /** Returns a set of entities by uid. Typically requested with a summary level of detail,
	 appropriate for card display and clustering. Full details are requested for 
	 detailed display views.
	 
	 Service URL: /entity/list
	 HTTP Method: POST and GET
	 
	 @param entities 	uids of entities to retrieve
	 @return 			entities */
  java.util.List<graphene.model.idl.G_Entity> getEntities(java.util.List<java.lang.String> entities, graphene.model.idl.G_LevelOfDetail levelOfDetail) throws org.apache.avro.AvroRemoteException;
  /** Returns a set of accounts for each given entity. Typically requested with a summary level of detail,
	 appropriate for card display and cluster summarisation. Full details are requested for 
	 detailed display views.
	 
	 Service URL: /entity/accounts
	 HTTP Method: POST and GET
	 
	 @param entities 		uids of entities to retrieve
	 @return 				map of input entity IDs to an array of the accounts for that entity */
  java.util.Map<java.lang.String,java.util.List<graphene.model.idl.G_Entity>> getAccounts(java.util.List<java.lang.String> entities) throws org.apache.avro.AvroRemoteException;
  /** Returns links to other entities related to specify entities. Each Link returned should
	 represent multiple records, used for displaying flow.
	 
	 CHANGED IN 1.6
	   - Added entityType filter
	 
	 See the specification for the Flow Data View.
	 See the specification for the Time Series Data View.
	 
	 Service URL: /link/flow
	 HTTP Method: POST and GET
	 
	 @param entities 		uids of entities to retrieve links for
	 @param focusEntities 	uids of focus entities (may be null)
	 @param direction 		one of: SOURCE, DESTINATION, BOTH
	 @param entityType		one of: ACCOUNT_OWNER, ACCOUNT, CLUSTER_SUMMARY, ANY
	 @param tag 			one of: FINANCIAL, SOCIAL, COMMUNICATION
	 @param date 			in the given date range
	 @return 				map of input entity IDs to an array of the links for that entity */
  java.util.Map<java.lang.String,java.util.List<graphene.model.idl.G_Link>> getFlowAggregation(java.util.List<java.lang.String> entities, java.util.List<java.lang.String> focusEntities, graphene.model.idl.G_DirectionFilter direction, graphene.model.idl.G_LinkEntityTypeFilter entityType, graphene.model.idl.G_LinkTag tag, graphene.model.idl.G_DateRange date) throws org.apache.avro.AvroRemoteException;
  /** Returns links to other entities related to specify entities.  Each Link returned should
	 represent multiple records, used for displaying time series.
	 
	 See the specification for the Time Series Data View.
	 
	 Service URL: /link/series
	 HTTP Method: POST and GET
	 
	 @param entities 		uids of entities to retrieve links for
	 @param focusEntities 	uids of focus entities (may be null)
	 @param tag 			one of: FINANCIAL, SOCIAL, COMMUNICATION
	 @param date 			in the given date range
	 @return 				map of input entity IDs to an array of the links for that entity */
  java.util.Map<java.lang.String,java.util.List<graphene.model.idl.G_Link>> getTimeSeriesAggregation(java.util.List<java.lang.String> entities, java.util.List<java.lang.String> focusEntities, graphene.model.idl.G_LinkTag tag, graphene.model.idl.G_DateRange date) throws org.apache.avro.AvroRemoteException;
  /** Returns links to other entities related to specify entities. Each link returned should
	 represent one raw transaction.  For financial data, these transactions will form a ledger.
	 For communications or social data, these are un-aggregated links for drill down by the user.
	 
	 CHANGED IN 1.6
	 	- return value type changed
	 	- added start index
	 
	 Service URL: /link/transactions
	 HTTP Method: POST and GET
	 
	 @param entities 		uids of entities to retrieve links for
	 @param direction 		one of: SOURCE, DESTINATION, BOTH
	 @param tag 			one of: FINANCIAL, SOCIAL, COMMUNICATION
	 @param date 			in the given date range
	 @param sort			Sort order for transactions
	 @param linkFilter		If provided, only return transactions where the "other side" is in this list
	 @param start			0-based index of first result to return
	 @param max				Trim the number of results this this value.
	 @return 				transactions for the set of entities */
  graphene.model.idl.G_TransactionResults getAllTransactions(java.util.List<java.lang.String> entities, graphene.model.idl.G_LinkTag tag, graphene.model.idl.G_DateRange date, graphene.model.idl.G_SortBy sort, java.util.List<java.lang.String> linkFilter, long start, long max) throws org.apache.avro.AvroRemoteException;

  @SuppressWarnings("all")
  /**  */
  public interface Callback extends G_DataAccess {
    public static final org.apache.avro.Protocol PROTOCOL = graphene.model.idl.G_DataAccess.PROTOCOL;
    /** Returns a set of entities by uid. Typically requested with a summary level of detail,
	 appropriate for card display and clustering. Full details are requested for 
	 detailed display views.
	 
	 Service URL: /entity/list
	 HTTP Method: POST and GET
	 
	 @param entities 	uids of entities to retrieve
	 @return 			entities */
    void getEntities(java.util.List<java.lang.String> entities, graphene.model.idl.G_LevelOfDetail levelOfDetail, org.apache.avro.ipc.Callback<java.util.List<graphene.model.idl.G_Entity>> callback) throws java.io.IOException;
    /** Returns a set of accounts for each given entity. Typically requested with a summary level of detail,
	 appropriate for card display and cluster summarisation. Full details are requested for 
	 detailed display views.
	 
	 Service URL: /entity/accounts
	 HTTP Method: POST and GET
	 
	 @param entities 		uids of entities to retrieve
	 @return 				map of input entity IDs to an array of the accounts for that entity */
    void getAccounts(java.util.List<java.lang.String> entities, org.apache.avro.ipc.Callback<java.util.Map<java.lang.String,java.util.List<graphene.model.idl.G_Entity>>> callback) throws java.io.IOException;
    /** Returns links to other entities related to specify entities. Each Link returned should
	 represent multiple records, used for displaying flow.
	 
	 CHANGED IN 1.6
	   - Added entityType filter
	 
	 See the specification for the Flow Data View.
	 See the specification for the Time Series Data View.
	 
	 Service URL: /link/flow
	 HTTP Method: POST and GET
	 
	 @param entities 		uids of entities to retrieve links for
	 @param focusEntities 	uids of focus entities (may be null)
	 @param direction 		one of: SOURCE, DESTINATION, BOTH
	 @param entityType		one of: ACCOUNT_OWNER, ACCOUNT, CLUSTER_SUMMARY, ANY
	 @param tag 			one of: FINANCIAL, SOCIAL, COMMUNICATION
	 @param date 			in the given date range
	 @return 				map of input entity IDs to an array of the links for that entity */
    void getFlowAggregation(java.util.List<java.lang.String> entities, java.util.List<java.lang.String> focusEntities, graphene.model.idl.G_DirectionFilter direction, graphene.model.idl.G_LinkEntityTypeFilter entityType, graphene.model.idl.G_LinkTag tag, graphene.model.idl.G_DateRange date, org.apache.avro.ipc.Callback<java.util.Map<java.lang.String,java.util.List<graphene.model.idl.G_Link>>> callback) throws java.io.IOException;
    /** Returns links to other entities related to specify entities.  Each Link returned should
	 represent multiple records, used for displaying time series.
	 
	 See the specification for the Time Series Data View.
	 
	 Service URL: /link/series
	 HTTP Method: POST and GET
	 
	 @param entities 		uids of entities to retrieve links for
	 @param focusEntities 	uids of focus entities (may be null)
	 @param tag 			one of: FINANCIAL, SOCIAL, COMMUNICATION
	 @param date 			in the given date range
	 @return 				map of input entity IDs to an array of the links for that entity */
    void getTimeSeriesAggregation(java.util.List<java.lang.String> entities, java.util.List<java.lang.String> focusEntities, graphene.model.idl.G_LinkTag tag, graphene.model.idl.G_DateRange date, org.apache.avro.ipc.Callback<java.util.Map<java.lang.String,java.util.List<graphene.model.idl.G_Link>>> callback) throws java.io.IOException;
    /** Returns links to other entities related to specify entities. Each link returned should
	 represent one raw transaction.  For financial data, these transactions will form a ledger.
	 For communications or social data, these are un-aggregated links for drill down by the user.
	 
	 CHANGED IN 1.6
	 	- return value type changed
	 	- added start index
	 
	 Service URL: /link/transactions
	 HTTP Method: POST and GET
	 
	 @param entities 		uids of entities to retrieve links for
	 @param direction 		one of: SOURCE, DESTINATION, BOTH
	 @param tag 			one of: FINANCIAL, SOCIAL, COMMUNICATION
	 @param date 			in the given date range
	 @param sort			Sort order for transactions
	 @param linkFilter		If provided, only return transactions where the "other side" is in this list
	 @param start			0-based index of first result to return
	 @param max				Trim the number of results this this value.
	 @return 				transactions for the set of entities */
    void getAllTransactions(java.util.List<java.lang.String> entities, graphene.model.idl.G_LinkTag tag, graphene.model.idl.G_DateRange date, graphene.model.idl.G_SortBy sort, java.util.List<java.lang.String> linkFilter, long start, long max, org.apache.avro.ipc.Callback<graphene.model.idl.G_TransactionResults> callback) throws java.io.IOException;
  }
}
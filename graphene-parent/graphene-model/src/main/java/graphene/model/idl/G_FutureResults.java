/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package graphene.model.idl;

@SuppressWarnings("all")
/** This defines an API for */
@org.apache.avro.specific.AvroGenerated
public interface G_FutureResults {
  public static final org.apache.avro.Protocol PROTOCOL = org.apache.avro.Protocol.parse("{\"protocol\":\"G_FutureResults\",\"namespace\":\"graphene.model.idl\",\"doc\":\"This defines an API for\",\"types\":[{\"type\":\"enum\",\"name\":\"G_PropertyTag\",\"doc\":\"Tags are defined by the application layer as a taxonomy of user and application concepts,\\r\\n\\t independent of the data sources. This allows application semantics to be re-used with new\\r\\n\\t data, with a minimum of new software design and development. Data layer entity types, link\\r\\n\\t types and properties should be mapped into the list of tags. The application layer must be\\r\\n\\t able to search by native field name or by tag interchangeably, and properties returned must\\r\\n\\t contain both native field names as well as tags.\\r\\n\\t \\r\\n\\t The list of tags may change as application features evolve, though that will require\\r\\n\\t collaboration with the data layer providers. Evolving the tag list should not change the\\r\\n\\t Data Access or Search APIs.\\r\\n\\r\\n\\t This is the current list of tags for Properties:\\r\\n\\t \\r\\n\\t CHANGED in 1.5:\\r\\n\\t   - CREDIT/DEBIT changed to INFLOWING/OUTFLOWING\\r\\n\\t   - added USD\\r\\n\\t   - added DURATION\\r\\n\\t   \\r\\n\\t CHANGED in 1.6:\\r\\n\\t   - added ENTITY_TYPE\\r\\n\\t   - added ACCOUNT_OWNER, CLUSTER_SUMMARY, COUNTRY_CODE\\r\\n\\t   \\r\\n\\t CHANGED in 1.7:\\r\\n\\t   - added CLUSTER\",\"symbols\":[\"ID\",\"TYPE\",\"ENTITY_TYPE\",\"ACCOUNT_OWNER\",\"CLUSTER_SUMMARY\",\"CLUSTER\",\"NAME\",\"LABEL\",\"STAT\",\"TEXT\",\"STATUS\",\"ANNOTATION\",\"WARNING\",\"LINKED_DATA\",\"IMAGE\",\"GEO\",\"COUNTRY_CODE\",\"DATE\",\"AMOUNT\",\"INFLOWING\",\"OUTFLOWING\",\"COUNT\",\"SERIES\",\"CONSTRUCTED\",\"RAW\",\"USD\",\"DURATION\"]},{\"type\":\"enum\",\"name\":\"G_EntityTag\",\"doc\":\"This is the current list of tags for Entities:\\r\\n\\t \\r\\n\\t CHANGED in 1.6:\\r\\n\\t   - added ACCOUNT_OWNER, CLUSTER_SUMMARY\",\"symbols\":[\"ACCOUNT_OWNER\",\"ACCOUNT\",\"GROUP\",\"CLUSTER\",\"CLUSTER_SUMMARY\",\"FILE\",\"ANONYMOUS\",\"PROMPT_FOR_DETAILS\",\"OTHER\"]},{\"type\":\"enum\",\"name\":\"G_LinkTag\",\"doc\":\"This is the current list of tags for Links:\",\"symbols\":[\"FINANCIAL\",\"SOCIAL\",\"COMMUNICATION\",\"OTHER\"]},{\"type\":\"enum\",\"name\":\"G_PropertyType\",\"doc\":\"Allowed types for Property values.\\r\\n\\r\\n\\t CHANGED in 1.5\",\"symbols\":[\"DOUBLE\",\"LONG\",\"BOOLEAN\",\"STRING\",\"DATE\",\"GEO\",\"OTHER\"]},{\"type\":\"record\",\"name\":\"G_Provenance\",\"doc\":\"This is a placeholder for future modeling of provenance. It is not a required field in any service calls.\",\"fields\":[{\"name\":\"uri\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"Placeholder for now. Express provenance as a single URI.\"}]},{\"type\":\"record\",\"name\":\"G_Uncertainty\",\"doc\":\"This is a placeholder for future modeling of uncertainty. It is not a required field in any service calls.\\r\\n\\t\\r\\n\\tCHANGED IN 1.6\",\"fields\":[{\"name\":\"confidence\",\"type\":\"double\",\"doc\":\"Placeholder for now. Express original source confidence as a single number from 0 to 1.\",\"default\":1}]},{\"type\":\"record\",\"name\":\"G_LinkedData\",\"doc\":\"A URL and MIME type representing a pointer to text, image or other external resource.\",\"fields\":[{\"name\":\"url\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"URL of the resource\"},{\"name\":\"mimeType\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"MIME type of the resource\",\"default\":null},{\"name\":\"title\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"description of the linked resource (suitable for display to the user in an hypertext link)\",\"default\":null}]},{\"type\":\"record\",\"name\":\"G_GeoData\",\"doc\":\"Structured representation of geo-spatial data.\",\"fields\":[{\"name\":\"text\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"an address or other place reference; unstructured text field\",\"default\":null},{\"name\":\"lat\",\"type\":[\"double\",\"null\"],\"doc\":\"latitude\",\"default\":null},{\"name\":\"lon\",\"type\":[\"double\",\"null\"],\"doc\":\"longitude\",\"default\":null},{\"name\":\"cc\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"ISO 3 digit country code\",\"default\":null}]},{\"type\":\"enum\",\"name\":\"G_ContinentCode\",\"doc\":\"Standard two letter continent code\\r\\n\\t  \\r\\n\\t ADDED IN 1.6\",\"symbols\":[\"AF\",\"AS\",\"EU\",\"NA\",\"SA\",\"OC\",\"AN\"]},{\"type\":\"record\",\"name\":\"G_Country\",\"doc\":\"Structured representation of country data, which includes geo-spatial data.\\r\\n\\t  \\r\\n\\t ADDED IN 1.6\",\"fields\":[{\"name\":\"country\",\"type\":\"G_GeoData\",\"doc\":\"country geo data, including the name as text\"},{\"name\":\"region\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"the name of the global region to which the country belongs; any common classification here is acceptable\"},{\"name\":\"continent\",\"type\":\"G_ContinentCode\",\"doc\":\"continent\"}]},{\"type\":\"enum\",\"name\":\"G_DateInterval\",\"doc\":\"Temporal resolution of a duration\\r\\n\\t \\r\\n\\t CHANGED IN 1.5\",\"symbols\":[\"SECONDS\",\"HOURS\",\"DAYS\",\"WEEKS\",\"MONTHS\",\"QUARTERS\",\"YEARS\"]},{\"type\":\"record\",\"name\":\"G_Duration\",\"doc\":\"A temporal duration\\r\\n\\t \\r\\n\\t ADDED IN 1.5\",\"fields\":[{\"name\":\"interval\",\"type\":\"G_DateInterval\",\"doc\":\"time aggregation level, e.g. use monthly data\"},{\"name\":\"numIntervals\",\"type\":\"long\",\"doc\":\"number of intervals, e.g. 12 monthly intervals is a 1 year duration\"}]},{\"type\":\"record\",\"name\":\"G_DateRange\",\"doc\":\"Describes a date range at a specific resolution.\\r\\n\\t \\r\\n\\t CHANGED IN 1.5\",\"fields\":[{\"name\":\"startDate\",\"type\":\"long\"},{\"name\":\"numBins\",\"type\":\"long\",\"doc\":\"number of bins to return, e.g. 12 monthly bins for 1 year of data\"},{\"name\":\"durationPerBin\",\"type\":\"G_Duration\",\"doc\":\"number of intervals in a bin, e.g. 2 months/bin in 12 bins for 2 years of data\"}]},{\"type\":\"enum\",\"name\":\"G_RangeType\",\"doc\":\"Allowed types for Ranges of values.\\r\\n\\t\\r\\n\\tCHANGED IN 1.6\",\"symbols\":[\"SINGLETON\",\"LIST\",\"BOUNDED\",\"DISTRIBUTION\"]},{\"type\":\"record\",\"name\":\"G_SingletonRange\",\"doc\":\"Single value\\r\\n\\t\\r\\n\\tADDED IN 1.5\",\"fields\":[{\"name\":\"value\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"int\",\"float\",\"double\",\"long\",\"boolean\",\"G_GeoData\"]},{\"name\":\"type\",\"type\":\"G_PropertyType\",\"doc\":\"One of DOUBLE, LONG, BOOLEAN, STRING, DATE, GEO, OTHER\"}]},{\"type\":\"record\",\"name\":\"G_ListRange\",\"doc\":\"List of values\\r\\n\\t\\r\\n\\tADDED IN 1.5\",\"fields\":[{\"name\":\"values\",\"type\":{\"type\":\"array\",\"items\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"int\",\"float\",\"double\",\"long\",\"boolean\",\"G_GeoData\"]}},{\"name\":\"type\",\"type\":\"G_PropertyType\",\"doc\":\"One of DOUBLE, LONG, BOOLEAN, STRING, DATE, GEO, OTHER\"}]},{\"type\":\"record\",\"name\":\"G_BoundedRange\",\"doc\":\"Bounded or unbounded range values\\r\\n\\t\\r\\n\\tADDED IN 1.5\",\"fields\":[{\"name\":\"start\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"int\",\"float\",\"double\",\"long\",\"boolean\",\"G_GeoData\",\"null\"],\"doc\":\"start of range, or null if unbounded start\"},{\"name\":\"end\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"int\",\"float\",\"double\",\"long\",\"boolean\",\"G_GeoData\",\"null\"],\"doc\":\"end of range, or null if unbounded start\"},{\"name\":\"inclusive\",\"type\":\"boolean\",\"doc\":\"If true, range includes specified endpoint. If false, range is exclusive.\"},{\"name\":\"type\",\"type\":\"G_PropertyType\",\"doc\":\"One of DOUBLE, LONG, BOOLEAN, STRING, DATE, GEO, OTHER\"}]},{\"type\":\"record\",\"name\":\"G_Frequency\",\"doc\":\"A frequency or probability element of a distribution.\\r\\n\\t \\r\\n\\tADDED IN 1.6\",\"fields\":[{\"name\":\"range\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"int\",\"float\",\"double\",\"long\",\"boolean\",\"G_GeoData\",\"G_ListRange\",\"G_BoundedRange\"],\"doc\":\"the value range which occurs with some specified frequency\"},{\"name\":\"frequency\",\"type\":\"double\",\"doc\":\"frequency as a count, or probability as a value from 0-1.\"}]},{\"type\":\"record\",\"name\":\"G_DistributionRange\",\"doc\":\"Describes a distribution of values. \\r\\n\\t \\r\\n\\tADDED IN 1.6\",\"fields\":[{\"name\":\"distribution\",\"type\":{\"type\":\"array\",\"items\":\"G_Frequency\"}},{\"name\":\"rangeType\",\"type\":\"G_RangeType\",\"doc\":\"Describes how the values in the distribution are summarised\"},{\"name\":\"type\",\"type\":\"G_PropertyType\",\"doc\":\"The type of value that the distribution describes. One of DOUBLE, LONG, BOOLEAN, STRING, DATE, GEO, OTHER\"},{\"name\":\"isProbability\",\"type\":\"boolean\",\"doc\":\"True if a probability distribution, false if a frequency distribution\",\"default\":false}]},{\"type\":\"record\",\"name\":\"G_Property\",\"doc\":\"Each property on an Entity or Link is a name-value pair, with data type informati","on, as well as optional\\r\\n\\t provenance. Tags provide a way for the data provider to associate semantic annotations to each property\\r\\n\\t in terms of the semantics of the application.  \\r\\n\\t \\r\\n\\t CHANGED IN 1.6\",\"fields\":[{\"name\":\"key\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"the field name in the underlying data source\"},{\"name\":\"friendlyText\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"user-friendly short-text for key (displayable)\",\"default\":null},{\"name\":\"range\",\"type\":[\"G_SingletonRange\",\"G_ListRange\",\"G_BoundedRange\",\"G_DistributionRange\"],\"doc\":\"range of values\",\"default\":null},{\"name\":\"provenance\",\"type\":[\"G_Provenance\",\"null\"],\"default\":null},{\"name\":\"uncertainty\",\"type\":[\"G_Uncertainty\",\"null\"],\"default\":null},{\"name\":\"tags\",\"type\":{\"type\":\"array\",\"items\":\"G_PropertyTag\"},\"doc\":\"one or more tags from the Tag list, used to map this source-specific field into the semantics of applications\"}]},{\"type\":\"record\",\"name\":\"G_Entity\",\"doc\":\"The nodes in the social, financial, communications or other graphs. May represent concrete individuals or organizations,\\r\\n\\t specific proxies such as accounts, or the implicit individuals or groups behind those other entities.\",\"fields\":[{\"name\":\"uid\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"This uid must represent either (1) a globally unique identifier that can be used to retrieve data for an\\r\\n\\t\\t\\texplicit entity, or (2) encoded query information that can be used to find a set of associated record\\r\\n\\t\\t\\tnotionally representing an implicit entity (e.g. Loans&FirstName&LastName&Gender). Must not be used for\\r\\n\\t\\t\\tIDs that aren't globally unique.  For example, in Kiva, \\\"Lenders\\\" has a UID (\\\"L12345\\\") while \\\"Borrowers\\\"\\r\\n\\t\\t\\thave an encoded search in the Loans table for uid (\\\"B{loan:23456;name=Daniel}\\\").  The encoded information\\r\\n\\t\\t\\tis data layer-specific, may be different from entity to entity or data set to data set, and should be\\r\\n\\t\\t\\tconsidered opaque to the consumers of the entities.  Entities of type 2 should always have the Entity Tag\\r\\n\\t\\t\\tANONYMOUS to help distinguish them when required.\"},{\"name\":\"tags\",\"type\":{\"type\":\"array\",\"items\":\"G_EntityTag\"},\"doc\":\"Entity Tags (see above, e.g. \\\"ACCOUNT\\\")\"},{\"name\":\"provenance\",\"type\":[\"G_Provenance\",\"null\"],\"default\":null},{\"name\":\"uncertainty\",\"type\":[\"G_Uncertainty\",\"null\"],\"default\":null},{\"name\":\"properties\",\"type\":{\"type\":\"array\",\"items\":\"G_Property\"}}]},{\"type\":\"record\",\"name\":\"G_Link\",\"doc\":\"* The links in the social, financial, communications or other graphs. May represent communication events, financial transactions\\r\\n\\t * or social connections.\",\"fields\":[{\"name\":\"tags\",\"type\":{\"type\":\"array\",\"items\":\"G_LinkTag\"},\"doc\":\"Link Tags (see above, e.g. \\\"FINANCIAL\\\")\"},{\"name\":\"source\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"source entity uid\",\"default\":null},{\"name\":\"target\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"target entity uid\",\"default\":null},{\"name\":\"directed\",\"type\":\"boolean\",\"doc\":\"true if directed, false if undirected\",\"default\":true},{\"name\":\"provenance\",\"type\":[\"G_Provenance\",\"null\"],\"default\":null},{\"name\":\"uncertainty\",\"type\":[\"G_Uncertainty\",\"null\"],\"default\":null},{\"name\":\"properties\",\"type\":{\"type\":\"array\",\"items\":\"G_Property\"}}]},{\"type\":\"record\",\"name\":\"G_Cluster\",\"doc\":\"Cluster of nodes in the social, financial, communications or other graphs. \\r\\n\\t \\r\\n\\t CHANGED in 1.7:\\r\\n\\t    - added version\",\"fields\":[{\"name\":\"uid\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"A unique identifier use to retrieve data about this cluster. Should not be used for non-global identifiers.\"},{\"name\":\"tags\",\"type\":{\"type\":\"array\",\"items\":\"G_EntityTag\"},\"doc\":\"Entity Tags (see DataTypes, e.g. \\\"CLUSTER\\\")\"},{\"name\":\"provenance\",\"type\":[\"G_Provenance\",\"null\"],\"default\":null},{\"name\":\"uncertainty\",\"type\":[\"G_Uncertainty\",\"null\"],\"default\":null},{\"name\":\"properties\",\"type\":{\"type\":\"array\",\"items\":\"G_Property\"}},{\"name\":\"members\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},\"doc\":\"List of id's of the members of this cluster\"},{\"name\":\"subclusters\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},\"doc\":\"List of id's of the subclusters of this cluster - empty if this is a leaf cluster in cluster hierarchy\"},{\"name\":\"parent\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"The id of the parent cluster if this is a cluster in a hierarchy - Can be null if this is a root cluster *\",\"default\":null},{\"name\":\"root\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"The id of the root cluster (top level cluster) if this is a cluster in a hierarchy - Can be null if this is a root cluster *\",\"default\":null},{\"name\":\"level\",\"type\":\"int\",\"doc\":\"The degree from the root this cluster is in the hierarchy - level = 0 if this is a root cluster *\",\"default\":0},{\"name\":\"version\",\"type\":\"int\",\"doc\":\"The version number of the cluster - each time the cluster contents is modified the version should be incremented *\",\"default\":1}]},{\"type\":\"record\",\"name\":\"G_Future\",\"doc\":\"Represents the future results of an asyncrhonous task.\\r\\n\\t Can be passed into the FutureResults service API\",\"fields\":[{\"name\":\"uid\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"unique id of this task\"},{\"name\":\"label\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"short human-readable description of task for display\"},{\"name\":\"service\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"],\"doc\":\"uid of the service task is running on\",\"default\":null},{\"name\":\"started\",\"type\":\"long\",\"doc\":\"date/time task was started\"},{\"name\":\"completed\",\"type\":\"long\",\"doc\":\"date/time task was completed (negative if not completed yet)\",\"default\":-1}]},{\"type\":\"record\",\"name\":\"G_Service\",\"doc\":\"Selectable services, returned by getServices() in various APIs\",\"fields\":[{\"name\":\"uid\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"unique id of this service\"},{\"name\":\"label\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"short human-readable description of service for display\"}]}],\"messages\":{\"setTimeout\":{\"doc\":\"Set a timeout for the task.\\r\\n\\t \\r\\n\\t Service URL: /future/timeout\\r\\n\\t HTTP Method: POST and GET\\r\\n\\t \\r\\n\\t @param future \\t\\tthe future returned from the original call\\r\\n\\t @param timeout\\t\\tonce set, task will terminate after specified time (in seconds)\",\"request\":[{\"name\":\"future\",\"type\":\"G_Future\"},{\"name\":\"timeout\",\"type\":\"long\"}],\"response\":\"null\"},\"getCompleted\":{\"doc\":\"Execution state of task.\\r\\n\\t \\r\\n\\t Service URL: /future/completed\\r\\n\\t HTTP Method: POST and GET\\r\\n\\t \\r\\n\\t @param future \\t\\tthe future returned from the original call\\r\\n\\t @return \\t\\t\\tfalse while still processing, true if completed (successfully or not)\",\"request\":[{\"name\":\"future\",\"type\":\"G_Future\"}],\"response\":\"boolean\"},\"getError\":{\"doc\":\"Error occured during task.\\r\\n\\t \\r\\n\\t Service URL: /future/error\\r\\n\\t HTTP Method: POST and GET\\r\\n\\t \\r\\n\\t @param future \\t\\tthe future returned from the original call\\r\\n\\t @return \\t\\t\\tif the task completed due to an error, a description of the error\",\"request\":[{\"name\":\"future\",\"type\":\"G_Future\"}],\"response\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\"]},\"getProgress\":{\"doc\":\"Estimated progress of task.\\r\\n\\t \\r\\n\\t Service URL: /future/progress\\r\\n\\t HTTP Method: POST and GET\\r\\n\\t \\r\\n\\t @param future \\t\\tthe future returned from the original call\\r\\n\\t @return \\t\\t\\testimated progress of the task (in seconds), negative if unknown\",\"request\":[{\"name\":\"future\",\"type\":\"G_Future\"}],\"response\":\"double\"},\"getExpectedDuration\":{\"doc\":\"Estimated duration of task, or time remaining (if in progress).\\r\\n\\t Must be a valid time. Use an estimated order of magnitude if exact times aren't known.\\r\\n\\t \\r\\n\\t Service URL: /future/duration\\r\\n\\t HTTP Method: POST and GET\\r\\n\\t \\r\\n\\t @param future \\t\\tthe future returned from the original call\\r\\n\\t @return \\t\\t\\testimated duration (or time remaining) of the task (in seconds)\",\"request\":[{\"name\":\"future","\",\"type\":\"G_Future\"}],\"response\":\"long\"},\"stop\":{\"doc\":\"Stop/cancel a running task. No effect if task is completed.\\r\\n\\t \\r\\n\\t Service URL: /future/stop\\r\\n\\t HTTP Method: POST and GET\\r\\n\\t \\r\\n\\t @param future \\t\\tthe future returned from the original call\",\"request\":[{\"name\":\"future\",\"type\":\"G_Future\"}],\"response\":\"null\"},\"getFutures\":{\"doc\":\"List the running (or recently completed but not garbage collected) tasks.\\r\\n\\t \\r\\n\\t Service URL: /future/list\\r\\n\\t HTTP Method: POST and GET\\r\\n\\t \\r\\n\\t @return\\t\\t\\tlist of running (or recently completed) futures\",\"request\":[],\"response\":{\"type\":\"array\",\"items\":\"G_Future\"}}}}");
  /** Set a timeout for the task.
	 
	 Service URL: /future/timeout
	 HTTP Method: POST and GET
	 
	 @param future 		the future returned from the original call
	 @param timeout		once set, task will terminate after specified time (in seconds) */
  java.lang.Void setTimeout(graphene.model.idl.G_Future future, long timeout) throws org.apache.avro.AvroRemoteException;
  /** Execution state of task.
	 
	 Service URL: /future/completed
	 HTTP Method: POST and GET
	 
	 @param future 		the future returned from the original call
	 @return 			false while still processing, true if completed (successfully or not) */
  boolean getCompleted(graphene.model.idl.G_Future future) throws org.apache.avro.AvroRemoteException;
  /** Error occured during task.
	 
	 Service URL: /future/error
	 HTTP Method: POST and GET
	 
	 @param future 		the future returned from the original call
	 @return 			if the task completed due to an error, a description of the error */
  java.lang.String getError(graphene.model.idl.G_Future future) throws org.apache.avro.AvroRemoteException;
  /** Estimated progress of task.
	 
	 Service URL: /future/progress
	 HTTP Method: POST and GET
	 
	 @param future 		the future returned from the original call
	 @return 			estimated progress of the task (in seconds), negative if unknown */
  double getProgress(graphene.model.idl.G_Future future) throws org.apache.avro.AvroRemoteException;
  /** Estimated duration of task, or time remaining (if in progress).
	 Must be a valid time. Use an estimated order of magnitude if exact times aren't known.
	 
	 Service URL: /future/duration
	 HTTP Method: POST and GET
	 
	 @param future 		the future returned from the original call
	 @return 			estimated duration (or time remaining) of the task (in seconds) */
  long getExpectedDuration(graphene.model.idl.G_Future future) throws org.apache.avro.AvroRemoteException;
  /** Stop/cancel a running task. No effect if task is completed.
	 
	 Service URL: /future/stop
	 HTTP Method: POST and GET
	 
	 @param future 		the future returned from the original call */
  java.lang.Void stop(graphene.model.idl.G_Future future) throws org.apache.avro.AvroRemoteException;
  /** List the running (or recently completed but not garbage collected) tasks.
	 
	 Service URL: /future/list
	 HTTP Method: POST and GET
	 
	 @return			list of running (or recently completed) futures */
  java.util.List<graphene.model.idl.G_Future> getFutures() throws org.apache.avro.AvroRemoteException;

  @SuppressWarnings("all")
  /** This defines an API for */
  public interface Callback extends G_FutureResults {
    public static final org.apache.avro.Protocol PROTOCOL = graphene.model.idl.G_FutureResults.PROTOCOL;
    /** Set a timeout for the task.
	 
	 Service URL: /future/timeout
	 HTTP Method: POST and GET
	 
	 @param future 		the future returned from the original call
	 @param timeout		once set, task will terminate after specified time (in seconds) */
    void setTimeout(graphene.model.idl.G_Future future, long timeout, org.apache.avro.ipc.Callback<java.lang.Void> callback) throws java.io.IOException;
    /** Execution state of task.
	 
	 Service URL: /future/completed
	 HTTP Method: POST and GET
	 
	 @param future 		the future returned from the original call
	 @return 			false while still processing, true if completed (successfully or not) */
    void getCompleted(graphene.model.idl.G_Future future, org.apache.avro.ipc.Callback<java.lang.Boolean> callback) throws java.io.IOException;
    /** Error occured during task.
	 
	 Service URL: /future/error
	 HTTP Method: POST and GET
	 
	 @param future 		the future returned from the original call
	 @return 			if the task completed due to an error, a description of the error */
    void getError(graphene.model.idl.G_Future future, org.apache.avro.ipc.Callback<java.lang.String> callback) throws java.io.IOException;
    /** Estimated progress of task.
	 
	 Service URL: /future/progress
	 HTTP Method: POST and GET
	 
	 @param future 		the future returned from the original call
	 @return 			estimated progress of the task (in seconds), negative if unknown */
    void getProgress(graphene.model.idl.G_Future future, org.apache.avro.ipc.Callback<java.lang.Double> callback) throws java.io.IOException;
    /** Estimated duration of task, or time remaining (if in progress).
	 Must be a valid time. Use an estimated order of magnitude if exact times aren't known.
	 
	 Service URL: /future/duration
	 HTTP Method: POST and GET
	 
	 @param future 		the future returned from the original call
	 @return 			estimated duration (or time remaining) of the task (in seconds) */
    void getExpectedDuration(graphene.model.idl.G_Future future, org.apache.avro.ipc.Callback<java.lang.Long> callback) throws java.io.IOException;
    /** Stop/cancel a running task. No effect if task is completed.
	 
	 Service URL: /future/stop
	 HTTP Method: POST and GET
	 
	 @param future 		the future returned from the original call */
    void stop(graphene.model.idl.G_Future future, org.apache.avro.ipc.Callback<java.lang.Void> callback) throws java.io.IOException;
    /** List the running (or recently completed but not garbage collected) tasks.
	 
	 Service URL: /future/list
	 HTTP Method: POST and GET
	 
	 @return			list of running (or recently completed) futures */
    void getFutures(org.apache.avro.ipc.Callback<java.util.List<graphene.model.idl.G_Future>> callback) throws java.io.IOException;
  }
}
/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package graphene.model.idl;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class UnauthorizedActionException extends org.apache.avro.specific.SpecificExceptionBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"error\",\"name\":\"UnauthorizedActionException\",\"namespace\":\"graphene.model.idl\",\"fields\":[{\"name\":\"message\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
   private java.lang.String message$;

  public UnauthorizedActionException() {
    super();
  }
  
  public UnauthorizedActionException(Object value) {
    super(value);
  }

  public UnauthorizedActionException(Throwable cause) {
    super(cause);
  }

  public UnauthorizedActionException(Object value, Throwable cause) {
    super(value, cause);
  }
  
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return message$;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: message$ = (java.lang.String)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'message$' field.
   */
  public java.lang.String getMessage$() {
    return message$;
  }

  /**
   * Sets the value of the 'message$' field.
   * @param value the value to set.
   */
  public void setMessage$(java.lang.String value) {
    this.message$ = value;
  }

  /** Creates a new UnauthorizedActionException RecordBuilder */
  public static graphene.model.idl.UnauthorizedActionException.Builder newBuilder() {
    return new graphene.model.idl.UnauthorizedActionException.Builder();
  }
  
  /** Creates a new UnauthorizedActionException RecordBuilder by copying an existing Builder */
  public static graphene.model.idl.UnauthorizedActionException.Builder newBuilder(graphene.model.idl.UnauthorizedActionException.Builder other) {
    return new graphene.model.idl.UnauthorizedActionException.Builder(other);
  }
  
  /** Creates a new UnauthorizedActionException RecordBuilder by copying an existing UnauthorizedActionException instance */
  public static graphene.model.idl.UnauthorizedActionException.Builder newBuilder(graphene.model.idl.UnauthorizedActionException other) {
    return new graphene.model.idl.UnauthorizedActionException.Builder(other);
  }
  
  /**
   * RecordBuilder for UnauthorizedActionException instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificErrorBuilderBase<UnauthorizedActionException>
    implements org.apache.avro.data.ErrorBuilder<UnauthorizedActionException> {

    private java.lang.String message$;

    /** Creates a new Builder */
    private Builder() {
      super(graphene.model.idl.UnauthorizedActionException.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(graphene.model.idl.UnauthorizedActionException.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.message$)) {
        this.message$ = data().deepCopy(fields()[0].schema(), other.message$);
        fieldSetFlags()[0] = true;
      }
    }
    
    /** Creates a Builder by copying an existing UnauthorizedActionException instance */
    private Builder(graphene.model.idl.UnauthorizedActionException other) {
      super(other);
      if (isValidValue(fields()[0], other.message$)) {
        this.message$ = data().deepCopy(fields()[0].schema(), other.message$);
        fieldSetFlags()[0] = true;
      }
    }

    @Override
    public graphene.model.idl.UnauthorizedActionException.Builder setValue(Object value) {
      super.setValue(value);
      return this;
    }
    
    @Override
    public graphene.model.idl.UnauthorizedActionException.Builder clearValue() {
      super.clearValue();
      return this;
    }

    @Override
    public graphene.model.idl.UnauthorizedActionException.Builder setCause(Throwable cause) {
      super.setCause(cause);
      return this;
    }
    
    @Override
    public graphene.model.idl.UnauthorizedActionException.Builder clearCause() {
      super.clearCause();
      return this;
    }

    /** Gets the value of the 'message$' field */
    public java.lang.String getMessage$() {
      return message$;
    }
    
    /** Sets the value of the 'message$' field */
    public graphene.model.idl.UnauthorizedActionException.Builder setMessage$(java.lang.String value) {
      validate(fields()[0], value);
      this.message$ = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'message$' field has been set */
    public boolean hasMessage$() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'message$' field */
    public graphene.model.idl.UnauthorizedActionException.Builder clearMessage$() {
      message$ = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    @Override
    public UnauthorizedActionException build() {
      try {
        UnauthorizedActionException record = new UnauthorizedActionException(getValue(), getCause());
        record.message$ = fieldSetFlags()[0] ? this.message$ : (java.lang.String) defaultValue(fields()[0]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}

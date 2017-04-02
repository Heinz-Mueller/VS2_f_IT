package VS2;


/**
* VS2/ConnectInformationDataHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from VS2.idl
* Sonntag, 26. März 2017 18:25 Uhr MESZ
*/

abstract public class ConnectInformationDataHelper
{
  private static String  _id = "IDL:VS2/ConnectInformationData:1.0";

  public static void insert (org.omg.CORBA.Any a, VS2.ConnectInformationData that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static VS2.ConnectInformationData extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [2];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[0] = new org.omg.CORBA.StructMember (
            "ip",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[1] = new org.omg.CORBA.StructMember (
            "port",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (VS2.ConnectInformationDataHelper.id (), "ConnectInformationData", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static VS2.ConnectInformationData read (org.omg.CORBA.portable.InputStream istream)
  {
    VS2.ConnectInformationData value = new VS2.ConnectInformationData ();
    value.ip = istream.read_string ();
    value.port = istream.read_long ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, VS2.ConnectInformationData value)
  {
    ostream.write_string (value.ip);
    ostream.write_long (value.port);
  }

}

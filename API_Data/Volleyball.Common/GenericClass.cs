using System.Collections.Generic;

public class GenericClass
{
    public string ReturnCode { get; set; } = "";
    public string ReturnMsg { get; set; } = "";
    public string ReturnValue { get; set; } = "";
    
  

    public object Data { get; set; } 

    public GenericClass()
    {
        Data = new object();
    }
}




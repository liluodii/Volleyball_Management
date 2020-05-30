using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Volleyball.Common
{
    public class ResponseMessages
    {
      
        public static string SuccessCode = "1";
        public static string SuccessMsg = "Successfull Transaction";

       
        public static string ErrorCode = "2";
        public static string ErrorMsg = "Error on server side";

       
        public static string AuthenticationFailedCode = "3";
        public static string AuthenticationFailedMsg = "Authentication Failed";

      
        public static string NoDataCode = "4";
        public static string NoDataMsg = "No Data Found";

        public static string UnsuccessCode = "5";
        public static string UnsuccessMsg = "Unsuccessful Transaction";

       





        public ResponseMessages()
        {
            //
            // TODO: Add constructor logic here
            //
        }

    }
}

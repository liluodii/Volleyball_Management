using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Volleyball.EF.CustomClasses
{
    public class CRequest
    {
        public class CReqUserLogin
        {
            public string APIKey { get; set; }
            public string EmailID { get; set; }
            public string Password { get; set; }

        }

    }
}

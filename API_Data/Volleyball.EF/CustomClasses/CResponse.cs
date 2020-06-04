
using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Threading.Tasks;

namespace Volleyball.EF.CustomClasses
{
    public class CResponse
    {
        public class CResUserLogin
        {
            public int UserID { get; set; }
            
            public string EmailID { get; set; }
            public string FirstName { get; set; }

            public string LastName { get; set; }


            public string ProfilePic { get; set; }
            public string Gender { get; set; }
            public string Contact { get; set; }
            public string DOB { get; set; }

            public string JoinDate { get; set; }
            public decimal? Experience { get; set; }
            public int? RoleID { get; set; }
            public string RoleName { get; set; }
            public string Address { get; set; }
        }

    }
}


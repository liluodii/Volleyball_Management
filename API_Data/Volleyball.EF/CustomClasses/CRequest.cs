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
        public class CReqChangePass
        {
            public string APIKey { get; set; }
            public int UserID { get; set; }
            public string Password { get; set; }

        }

        public class CReqDeleteUser
        {
            public string APIKey { get; set; }
            public int UserID { get; set; }
           

        }

        public class CReqUpdateProfile
        {
            public string APIKey { get; set; }
            public int UserID { get; set; } = 0;

            public string EmailID { get; set; }
            public string FirstName { get; set; }

            public string LastName { get; set; }

            public string Gender { get; set; }
            public string Contact { get; set; }
            public string DOB { get; set; }

            public string JoinDate { get; set; }
            public decimal? Experience { get; set; }
           
            public string Address { get; set; }
        }


        public class CReqForgetPassword
        {
            public string APIKey { get; set; }
            public string Email { get; set; }
        }

    }
}

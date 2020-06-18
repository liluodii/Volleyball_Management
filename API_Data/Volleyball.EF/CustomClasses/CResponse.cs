
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
            public int UserID { get; set; } = 0;

            public string EmailID { get; set; } = "";
            public string FirstName { get; set; } = "";

            public string LastName { get; set; } = "";


            public string ProfilePic { get; set; } = "";
            public string Gender { get; set; } = "";
            public string Contact { get; set; } = "";
            public string DOB { get; set; } = "";

            public string JoinDate { get; set; } = "";
            public decimal? Experience { get; set; } = 0;
            public int? RoleID { get; set; } = 0;
            public string RoleName { get; set; } = "";
            public string Address { get; set; } = "";
        }


        public class CResMatch
        {
            public int TournamentTeamID { get; set; } = 0;
            public int? Team1 { get; set; }
            public int? Team2 { get; set; }
            public int? Team1Score { get; set; }
            public int? Team2Score { get; set; }
            public string Team1Name { get; set; }
            public string Team2Name { get; set; }
            public string Team1Pic { get; set; }
            public string Team2Pic { get; set; }
            public string MatchDate { get; set; }
        }

        public class CResGetMatch
        {
            public List<CResMatch> Upcomming { get; set; } = new List<CResMatch>();
            public List<CResMatch> Running { get; set; } = new List<CResMatch>();
            public List<CResMatch> Commpleted { get; set; } = new List<CResMatch>();

        }

    }
}


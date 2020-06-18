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
            public string OldPass { get; set; }
            public string NewPass { get; set; }



        }

        public class CReqDeleteUser
        {
            public string APIKey { get; set; }
            public int UserID { get; set; }


        }
        public class CReqDeleteTournament
        {
            public string APIKey { get; set; }
            public int TournamentID { get; set; }


        }

        public class CReqDeleteTeam
        {
            public string APIKey { get; set; }
            public int UserID { get; set; }
            public int TeamID { get; set; }
        }

        public class CReqAddTeamMemberInTeam
        {
            public string APIKey { get; set; }
            public int UserID { get; set; }
            public string PlayerIDs { get; set; }
            public int TeamID { get; set; }
        }
        public class CReqDeletePlayerFromTeam
        {
            public string APIKey { get; set; }
            public int UserID { get; set; }
            public int TeamMemberJoinID { get; set; }
        }
        public class CReqUpdateProfile
        {
            public string APIKey { get; set; }
            public int UserID { get; set; } = 0;

            public string EmailID { get; set; }
            public string FirstName { get; set; }

            public string Password { get; set; }
            public string LastName { get; set; }

            public string Gender { get; set; }
            public string Contact { get; set; }
            public DateTime? DOB { get; set; }

            public DateTime? JoinDate { get; set; }
            public decimal? Experience { get; set; }

            public string Address { get; set; }
        }
        public class CReqAddEditTournament
        {
            public string APIKey { get; set; }
            public int TournamentID { get; set; } = 0;

            public int UserID { get; set; } = 0;
            public string Name { get; set; }
            public DateTime? StartDate { get; set; }
            public DateTime? EndDate { get; set; }


        }
        public class CReqAddEditTournamentTeam
        {
            public string APIKey { get; set; }
            public int TournamentTeamID { get; set; } = 0;

            public int TournamentID { get; set; } = 0;
            public int UserID { get; set; } = 0;
            public DateTime? StartDate { get; set; }
            public int Team1 { get; set; }
            public int Team2 { get; set; }


        }


        public class CReqAddEditTeamManager
        {
            public string APIKey { get; set; }
            public int UserID { get; set; } = 0;

            public string EmailID { get; set; }
            public string FirstName { get; set; }

            public string Password { get; set; }
            public string LastName { get; set; }

            public string Gender { get; set; }
            public string Contact { get; set; }
            public string DOB { get; set; }

            public string JoinDate { get; set; }

            public string Address { get; set; }
        }




        public class CReqForgetPassword
        {
            public string APIKey { get; set; }
            public string Email { get; set; }
        }

    }
}

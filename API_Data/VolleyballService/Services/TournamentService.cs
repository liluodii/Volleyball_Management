using System;
using System.Linq;
using System.Web;
using System.Data;
using System.Collections.Generic;
using static Volleyball.EF.CustomClasses.CRequest;
using static Volleyball.EF.CustomClasses.CResponse;
using Volleyball.EF;
using Volleyball.Common;
using System.Net;
using System.IO;
using System.Net.Mail;
using System.Net.Mime;
using System.Runtime.InteropServices.ComTypes;

namespace VolleyballService.Services
{
    public class TournamentService : CConnect
    {

        public GenericClass AddEditTournamet(CReqAddEditTournament Data)
        {
            GenericClass obj = new GenericClass();

            try
            {


                if (Data.TournamentID != 0)
                {
                    Tournament TM = (from u in DC.Tournaments
                                     where u.ID == Data.TournamentID
                                     select u).FirstOrDefault();



                    if (TM != null)
                    {
                        TM.EndDate = Data.EndDate;
                        TM.ModifiedDate = System.DateTime.UtcNow;
                        TM.Name = Data.Name;
                        TM.StartDate = Data.StartDate;
                        DC.SaveChanges();
                        obj.ReturnMsg = "Successfully update tournament.";

                    }
                    else
                    {
                        obj.ReturnCode = ResponseMessages.NoDataCode;
                        obj.ReturnMsg = "Tournament does not exist";

                        return obj;
                    }

                }

                else
                {


                    Tournament TM = new Tournament();

                    TM.StartDate = Data.StartDate;
                    TM.EndDate = Data.EndDate;
                    TM.Name = Data.Name;
                    TM.CreatedDate = System.DateTime.UtcNow;
                    DC.Tournaments.Add(TM);
                    DC.SaveChanges();
                    obj.ReturnMsg = "Successfully add Tournament.";
                }
                obj.ReturnCode = ResponseMessages.SuccessCode;

            }
            catch (Exception EX)
            {

                throw;
            }

            return obj;
        }

        public GenericClass DeleteTournament(CReqDeleteTournament Data)
        {
            GenericClass obj = new GenericClass();

            try
            {
                Tournament tournament = (from u in DC.Tournaments
                                         where u.ID == Data.TournamentID
                                         select u).FirstOrDefault();
                if (tournament != null)
                {

                    DC.Tournaments.Remove(tournament);
                    DC.SaveChanges();

                }
                else
                {
                    obj.ReturnCode = ResponseMessages.NoDataCode;
                    obj.ReturnMsg = "Tournament does not exist";
                    return obj;
                }
                obj.ReturnCode = ResponseMessages.SuccessCode;
                obj.ReturnMsg = "Tournament deleted successfully";

            }
            catch (Exception EX)
            {

                throw;
            }
            return obj;
        }


        public GenericClass AddEditTournamentTeam(CReqAddEditTournamentTeam Data)
        {
            GenericClass obj = new GenericClass();

            try
            {


                if (Data.TournamentTeamID != 0)
                {
                    TournamentTeam TM = (from u in DC.TournamentTeams
                                         where u.ID == Data.TournamentID
                                         select u).FirstOrDefault();



                    if (TM != null)
                    {
                        TM.MatchDate = Data.StartDate;
                        TM.Team1 = Data.Team1;
                        TM.Team2 = Data.Team2;
                        TM.TournamentID = Data.TournamentID;
                        TM.CreatedDate = System.DateTime.UtcNow;

                        DC.SaveChanges();
                        obj.ReturnMsg = "Successfully update tournament team.";

                    }
                    else
                    {
                        obj.ReturnCode = ResponseMessages.NoDataCode;
                        obj.ReturnMsg = "Team does not exist";

                        return obj;
                    }

                }

                else
                {


                    TournamentTeam TM = new TournamentTeam();

                    TM.MatchDate = Data.StartDate;
                    TM.Team1 = Data.Team1;
                    TM.Team2 = Data.Team2;
                    TM.TournamentID = Data.TournamentID;
                    TM.CreatedDate = System.DateTime.UtcNow;

                    DC.TournamentTeams.Add(TM);
                    DC.SaveChanges();
                    obj.ReturnMsg = "Successfully add tournament team.";
                }
                obj.ReturnCode = ResponseMessages.SuccessCode;

            }
            catch (Exception EX)
            {

                throw;
            }

            return obj;
        }

    }
}

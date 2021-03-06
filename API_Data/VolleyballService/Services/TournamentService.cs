﻿using System;
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
                if (Data.StartDate.Value.Date < System.DateTime.UtcNow.Date)
                {
                    obj.ReturnCode = ResponseMessages.NoDataCode;
                    obj.ReturnMsg = "Select another date.";
                    return obj;
                }

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
                        TM.Team1Score = 0;
                        TM.Team2Score = 0;
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
                    var t1 = DC.TournamentTeams.AsEnumerable().Where(x => x.Team1 == Data.Team1 && x.MatchDate.Value.Date == Data.StartDate.Value.Date).FirstOrDefault();

                    if (t1 != null)
                    {
                        obj.ReturnCode = ResponseMessages.NoDataCode;
                        obj.ReturnMsg = t1.Team.TeamName + " already have match on this date.";
                        return obj;
                    }

                    var t2 = DC.TournamentTeams.AsEnumerable().Where(x => x.Team2 == Data.Team2 && x.MatchDate.Value.Date == Data.StartDate.Value.Date).FirstOrDefault();

                    if (t2 != null)
                    {
                        obj.ReturnCode = ResponseMessages.NoDataCode;
                        obj.ReturnMsg = t2.Team.TeamName + " already have match on this date.";
                        return obj;
                    }

                    TournamentTeam TM = new TournamentTeam();

                    TM.MatchDate = Data.StartDate;
                    TM.Team1 = Data.Team1;
                    TM.Team2 = Data.Team2;
                    TM.Team1Score = 0;
                    TM.Team2Score = 0;
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

        public GenericClass UpdateScore(CReqUpdateScore Data)
        {

            GenericClass obj = new GenericClass();

            try
            {

                TournamentTeam TM = (from u in DC.TournamentTeams
                                     where u.ID == Data.TournamentTeamID
                                     select u).FirstOrDefault();



                if (TM != null)
                {
                    TM.Team1Score = Data.Team1Score;
                    TM.Team2Score = Data.Team2Score;

                    DC.SaveChanges();
                    obj.ReturnMsg = "Score updated successfully.";

                }
                else
                {
                    obj.ReturnCode = ResponseMessages.NoDataCode;
                    obj.ReturnMsg = "Team does not exist";

                    return obj;
                }
                obj.ReturnCode = ResponseMessages.SuccessCode;
            }
            catch (Exception EX)
            {

                throw;
            }

            return obj;
        }

        public GenericClass GetMatchList()
        {
            GenericClass obj = new GenericClass();
            try
            {
                CResGetMatch list = new CResGetMatch();
                var MatchList = DC.TournamentTeams.ToList();

                DateTime CurrentDate = System.DateTime.UtcNow.Date;

                list.Running = (from u in MatchList.AsEnumerable()
                                where u.MatchDate.Value.Date == CurrentDate.Date && u.Team != null && u.Team3 != null
                                select new CResMatch
                                {
                                    MatchDate = u.MatchDate.Value.ToString("MM-dd-yyyy"),
                                    Team1 = u.Team1,
                                    Team2 = u.Team2,
                                    Team1Score = u.Team1Score == null ? 0 : u.Team1Score,
                                    Team2Score = u.Team2Score == null ? 0 : u.Team2Score,
                                    Team1Name = u.Team.TeamName,
                                    Team2Name = u.Team3.TeamName,
                                    Team1Pic = string.IsNullOrEmpty(u.Team.TeamPic) ? "" : BaseService.GetURL() + u.Team.TeamPic,
                                    Team2Pic = string.IsNullOrEmpty(u.Team3.TeamPic) ? "" : BaseService.GetURL() + u.Team3.TeamPic,
                                    TournamentTeamID = u.ID,
                                }).ToList();

                list.Upcomming = (from u in MatchList.AsEnumerable()
                                  where CurrentDate.Date < u.MatchDate.Value.Date && u.Team != null && u.Team3 != null
                                  select new CResMatch
                                  {
                                      MatchDate = u.MatchDate.Value.ToString("MM-dd-yyyy"),
                                      Team1 = u.Team1,
                                      Team2 = u.Team2,
                                      Team1Score = u.Team1Score == null ? 0 : u.Team1Score,
                                      Team2Score = u.Team2Score == null ? 0 : u.Team2Score,
                                      Team1Name = u.Team.TeamName,
                                      Team2Name = u.Team3.TeamName,
                                      Team1Pic = string.IsNullOrEmpty(u.Team.TeamPic) ? "" : BaseService.GetURL() + u.Team.TeamPic,
                                      Team2Pic = string.IsNullOrEmpty(u.Team3.TeamPic) ? "" : BaseService.GetURL() + u.Team3.TeamPic,
                                      TournamentTeamID = u.ID,
                                  }).ToList();

                list.Commpleted = (from u in MatchList.AsEnumerable()
                                   where CurrentDate.Date > u.MatchDate.Value.Date && u.Team != null && u.Team3 != null
                                   select new CResMatch
                                   {
                                       MatchDate = u.MatchDate.Value.ToString("MM-dd-yyyy"),
                                       Team1 = u.Team1,
                                       Team2 = u.Team2,
                                       WinnerTeam = u.Team1Score == u.Team2Score ? -1 : (u.Team1Score >= u.Team2Score ? u.Team1 : u.Team2),
                                       Team1Score = u.Team1Score == null ? 0 : u.Team1Score,
                                       Team2Score = u.Team2Score == null ? 0 : u.Team2Score,
                                       Team1Name = u.Team.TeamName,
                                       Team2Name = u.Team3.TeamName,
                                       Team1Pic = string.IsNullOrEmpty(u.Team.TeamPic) ? "" : BaseService.GetURL() + u.Team.TeamPic,
                                       Team2Pic = string.IsNullOrEmpty(u.Team3.TeamPic) ? "" : BaseService.GetURL() + u.Team3.TeamPic,
                                       TournamentTeamID = u.ID,
                                   }).ToList();

                obj.Data = list;
                obj.ReturnCode = ResponseMessages.SuccessCode;
                obj.ReturnMsg = ResponseMessages.SuccessMsg;



            }
            catch (Exception)
            {

                throw;
            }

            return obj;
        }

        public GenericClass GetTournamentList()
        {
            GenericClass obj = new GenericClass();
            try
            {
                var List = (from u in DC.Tournaments.AsEnumerable()
                            orderby u.CreatedDate
                            select new
                            {
                                TournamentID = u.ID,
                                Name = u.Name
                            }).ToList();

                if (List.Count() > 0)
                {
                    obj.Data = List;
                    obj.ReturnCode = ResponseMessages.SuccessCode;
                    obj.ReturnMsg = ResponseMessages.SuccessMsg;
                }
                else
                {
                    obj.ReturnCode = ResponseMessages.NoDataCode;
                    obj.ReturnMsg = "Tournament list not avaialble.";
                    obj.Data = new List<int>();
                    return obj;
                }

            }
            catch (Exception)
            {

                throw;
            }
            return obj;


        }

        public GenericClass GetTournamentTeam(CReqGetTournament Data)
        {
            GenericClass obj = new GenericClass();
            try
            {
                var List = (from u in DC.TournamentTeams.AsEnumerable()
                            where u.TournamentID == Data.TournamentTeamID && u.Team1 != null && u.Team2 != null
                            orderby u.CreatedDate
                            select new CResMatch
                            {
                                MatchDate = u.MatchDate.Value.ToString("MM-dd-yyyy"),
                                Team1 = u.Team1,
                                Team2 = u.Team2,
                                Team1Score = u.Team1Score,
                                Team2Score = u.Team2Score,
                                Team1Name = u.Team.TeamName,
                                Team2Name = u.Team3.TeamName,
                                Team1Pic = string.IsNullOrEmpty(u.Team.TeamPic) ? "" : BaseService.GetURL() + u.Team.TeamPic,
                                Team2Pic = string.IsNullOrEmpty(u.Team3.TeamPic) ? "" : BaseService.GetURL() + u.Team3.TeamPic,
                                TournamentTeamID = u.ID,
                            }).ToList();

                if (List.Count() > 0)
                {
                    obj.Data = List;
                    obj.ReturnCode = ResponseMessages.SuccessCode;
                    obj.ReturnMsg = ResponseMessages.SuccessMsg;


                }
                else
                {
                    obj.ReturnCode = ResponseMessages.NoDataCode;
                    obj.ReturnMsg = "Tournament team not available";
                    obj.Data = new List<int>();
                    return obj;
                }

            }
            catch (Exception)
            {

                throw;
            }
            return obj;

        }

        public GenericClass DeleteTournamentTeam(CReqDeleteTournamentTeam Data)
        {
            GenericClass obj = new GenericClass();

            try
            {
                TournamentTeam tournament = (from u in DC.TournamentTeams
                                             where u.ID == Data.TournamentTeamID
                                             select u).FirstOrDefault();
                if (tournament != null)
                {

                    DC.TournamentTeams.Remove(tournament);
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

    }
}

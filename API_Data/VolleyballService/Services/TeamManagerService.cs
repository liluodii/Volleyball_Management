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
    public class TeamManagerService : CConnect
    {

        public GenericClass AddEditPlayer(CReqUpdateProfile Data)
        {
            GenericClass obj = new GenericClass();

            try
            {


                if (Data.UserID != 0)
                {
                    UserMaster user = (from u in DC.UserMasters
                                       where u.ID == Data.UserID
                                       select u).FirstOrDefault();

                  

                    if (user != null)
                    {
                        PlayerMaster PM = user.PlayerMasters?.FirstOrDefault();
                        PM.Address = Data.Address;
                        PM.Contact = Data.Contact;
                        PM.DOB = Convert.ToDateTime(Data.DOB);
                        PM.FirstName = Data.FirstName;
                        PM.LastName = Data.LastName;
                        PM.JoinDate = Convert.ToDateTime(Data.JoinDate);
                        PM.Gender = Data.Gender;
                        PM.Experience = Data.Experience;
                        PM.ModifiedDate = System.DateTime.UtcNow;
                        DC.SaveChanges();
                        obj.ReturnMsg = "Successfully update profile.";
                        obj.ReturnValue = PM.UserID.ToString();

                    }
                    else
                    {
                        obj.ReturnCode = ResponseMessages.NoDataCode;
                        obj.ReturnMsg = "User does not exist";
                    
                        return obj;
                    }

                }

                else
                {

                    UserMaster UM = new UserMaster();
                    UM.CreatedDate = System.DateTime.UtcNow;
                    UM.EmailID = Data.EmailID;
                    UM.Password = Data.Password;
                    UM.RoleID = 4;

                    DC.UserMasters.Add(UM);
                    DC.SaveChanges();


                    PlayerMaster PM = new PlayerMaster();
                    PM.UserID = UM.ID;
                    PM.Address = Data.Address;
                    PM.Contact = Data.Contact;
                    PM.DOB = Convert.ToDateTime(Data.DOB);
                    PM.FirstName = Data.FirstName;
                    PM.LastName = Data.LastName;
                    PM.JoinDate = Convert.ToDateTime(Data.JoinDate);
                    PM.Gender = Data.Gender;
                    PM.Experience = Data.Experience;
                    PM.CreatedDate = System.DateTime.UtcNow;
                    DC.PlayerMasters.Add(PM);
                    DC.SaveChanges();
                    obj.ReturnMsg = "Successfully add profile.";
                    obj.ReturnValue = PM.UserID.ToString();



                }
                obj.ReturnCode = ResponseMessages.SuccessCode;

            }
            catch (Exception EX)
            {

                throw;
            }

            return obj;
        }


        public GenericClass DeletePlayer(CReqDeleteUser Data)
        {
            GenericClass obj = new GenericClass();

            try
            {



                UserMaster user = (from u in DC.UserMasters
                                   where u.ID == Data.UserID
                                   select u).FirstOrDefault();
                if (user != null)
                {
                    PlayerMaster PM = user.PlayerMasters.FirstOrDefault();
                    if (PM != null)
                    {
                        DC.UserMasters.Remove(user);
                        DC.SaveChanges();
                    }
                    else
                    {
                        obj.ReturnCode = ResponseMessages.NoDataCode;
                        obj.ReturnMsg = "User does not exist";
                        return obj;
                    }

                }
                else
                {
                    obj.ReturnCode = ResponseMessages.NoDataCode;
                    obj.ReturnMsg = "User does not exist";
                    return obj;
                }
                obj.ReturnCode = ResponseMessages.SuccessCode;
                obj.ReturnMsg = "Player deleted successfully";

            }
            catch (Exception EX)
            {

                throw;
            }

            return obj;
        }


        public GenericClass AddEditTeamManager(CReqAddEditTeamManager Data)
        {
            GenericClass obj = new GenericClass();

            try
            {


                if (Data.UserID != 0)
                {
                    UserMaster user = (from u in DC.UserMasters
                                       where u.ID == Data.UserID
                                       select u).FirstOrDefault();

                   

                    if (user != null)
                    {
                        TeamManager TM = user.TeamManagers?.FirstOrDefault();
                        TM.Address = Data.Address;
                        TM.Contact = Data.Contact;
                        TM.DOB = Convert.ToDateTime(Data.DOB);
                        TM.FirstName = Data.FirstName;
                        TM.LastName = Data.LastName;
                        TM.JoinDate = Convert.ToDateTime(Data.JoinDate);
                        TM.Gender = Data.Gender;

                        TM.ModifiedDate = System.DateTime.UtcNow;
                        DC.SaveChanges();
                        obj.ReturnMsg = "Successfully update profile.";

                    }
                    else
                    {
                        obj.ReturnCode = ResponseMessages.NoDataCode;
                        obj.ReturnMsg = "User does not exist";
                        return obj;
                    }

                }

                else
                {

                    UserMaster UM = new UserMaster();
                    UM.CreatedDate = System.DateTime.UtcNow;
                    UM.EmailID = Data.EmailID;
                    UM.Password = Data.Password;
                    UM.RoleID = 3;

                    DC.UserMasters.Add(UM);
                    DC.SaveChanges();


                    TeamManager TM = new TeamManager();
                    TM.UserID = UM.ID;
                    TM.Address = Data.Address;
                    TM.Contact = Data.Contact;
                    TM.DOB = Convert.ToDateTime(Data.DOB);
                    TM.FirstName = Data.FirstName;
                    TM.LastName = Data.LastName;
                    TM.JoinDate = Convert.ToDateTime(Data.JoinDate);
                    TM.Gender = Data.Gender;

                    TM.CreatedDate = System.DateTime.UtcNow;
                    DC.TeamManagers.Add(TM);
                    DC.SaveChanges();
                    obj.ReturnMsg = "Successfully add profile.";


                }
                obj.ReturnCode = ResponseMessages.SuccessCode;

            }
            catch (Exception EX)
            {

                throw;
            }

            return obj;
        }


        public GenericClass DeleteTeamManager(CReqDeleteUser Data)
        {
            GenericClass obj = new GenericClass();

            try
            {
                UserMaster user = (from u in DC.UserMasters
                                   where u.ID == Data.UserID
                                   select u).FirstOrDefault();
                if (user != null)
                {
                    TeamManager TM = user.TeamManagers.FirstOrDefault();
                    if (TM != null)
                    {
                        DC.UserMasters.Remove(user);
                        DC.SaveChanges();
                    }
                    else
                    {
                        obj.ReturnCode = ResponseMessages.NoDataCode;
                        obj.ReturnMsg = "User does not exist";
                        return obj;
                    }
                }
                else
                {
                    obj.ReturnCode = ResponseMessages.NoDataCode;
                    obj.ReturnMsg = "User does not exist";
                    return obj;
                }
                obj.ReturnCode = ResponseMessages.SuccessCode;
                obj.ReturnMsg = "Team manager deleted successfully";

            }
            catch (Exception EX)
            {

                throw;
            }
            return obj;
        }


        public GenericClass GetPlayerList(int UserID, string Name)
        {
            GenericClass obj = new GenericClass();

            try
            {



                UserMaster user = (from u in DC.UserMasters
                                   where u.ID == UserID
                                   select u).FirstOrDefault();
                if (user != null)
                {
                    if (user.RoleID == 2)
                    {
                        var ret = (from t in DC.PlayerMasters.AsEnumerable()
                                   where string.IsNullOrEmpty(Name) ? true : (t.FirstName.Contains(Name) || t.LastName.Contains(Name))
                                   select new
                                   {
                                       PlayerID = t.ID,
                                       Name = t.FirstName + " " + t.LastName,
                                       Photo = string.IsNullOrEmpty(t.ProfilePic) ? "" : BaseService.GetURL() + t.ProfilePic

                                   });
                        obj.Data = ret;
                    }
                    else if (user.RoleID == 2)
                    {
                        var ret = (from t in DC.TeamMembers.AsEnumerable()
                                   where (t.Team.TeamManagerID == user.TeamManagers.FirstOrDefault().ID) && (string.IsNullOrEmpty(Name) ? true : t.PlayerMaster.FirstName.Contains(Name) || t.PlayerMaster.LastName.Contains(Name))
                                   select new
                                   {
                                       PlayerID = t.PlayerID,
                                       Name = t.PlayerMaster.FirstName + " " + t.PlayerMaster.LastName,
                                       Photo = string.IsNullOrEmpty(t.PlayerMaster.ProfilePic) ? "" : BaseService.GetURL() + t.PlayerMaster.ProfilePic

                                   });
                        obj.Data = ret;

                    }

                }
                else
                {
                    obj.ReturnCode = ResponseMessages.NoDataCode;
                    obj.ReturnMsg = "User does not exist";
                    obj.Data = new List<int>();
                    return obj;
                }
                obj.ReturnCode = ResponseMessages.SuccessCode;
                obj.ReturnMsg = ResponseMessages.SuccessMsg;

            }
            catch (Exception EX)
            {
                throw;
            }

            return obj;
        }

    }


}

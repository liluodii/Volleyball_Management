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
                    PlayerMaster PM = (from u in DC.PlayerMasters
                                       where u.ID == Data.UserID
                                       select u).FirstOrDefault();



                    if (PM != null)
                    {
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
                    UserMaster user = (from u in DC.UserMasters.AsEnumerable()
                                       where u.EmailID.ToLower() == Data.EmailID.ToLower()
                                       select u).FirstOrDefault();

                    if (user != null)
                    {
                        obj.ReturnCode = ResponseMessages.NoDataCode;
                        obj.ReturnMsg = "Email id already exist.";
                        return obj;
                    }



                    Random rnd = new Random();

                    //string password = rnd.Next(111111, 999999).ToString();
                    string password = "123456";

                    UserMaster UM = new UserMaster();
                    UM.CreatedDate = System.DateTime.UtcNow;
                    UM.EmailID = Data.EmailID;
                    UM.Password = password;
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

                    //SendRegistrationEmail(Data.EmailID, password, Data.FirstName + " " + Data.LastName);

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
                    TeamManager TM = (from u in DC.TeamManagers
                                       where u.ID == Data.UserID
                                       select u).FirstOrDefault();



                    if (TM != null)
                    {
                        
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
                        obj.ReturnValue = TM.UserID.ToString();


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
                    UserMaster user = (from u in DC.UserMasters.AsEnumerable()
                                       where u.EmailID.ToLower() == Data.EmailID.ToLower()
                                       select u).FirstOrDefault();

                    if (user != null)
                    {
                        obj.ReturnCode = ResponseMessages.NoDataCode;
                        obj.ReturnMsg = "Email id already exist.";
                        return obj;
                    }

                    Random rnd = new Random();

                    //string password = rnd.Next(111111, 999999).ToString();
                    string password = "123456";

                    UserMaster UM = new UserMaster();
                    UM.CreatedDate = System.DateTime.UtcNow;
                    UM.EmailID = Data.EmailID;
                    UM.Password = password;
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

                    SendRegistrationEmail(Data.EmailID, password, Data.FirstName + " " + Data.LastName);

                    obj.ReturnMsg = "Successfully add profile.";
                    obj.ReturnValue = UM.ID.ToString();



                }
                obj.ReturnCode = ResponseMessages.SuccessCode;

            }
            catch (Exception EX)
            {

                throw;
            }

            return obj;
        }

        public GenericClass GetTeamManagerDetails(int TeamManagerID)
        {
            GenericClass obj = new GenericClass();
            try
            {
                var user = DC.TeamManagers.Where(x => x.ID == TeamManagerID).FirstOrDefault();
                if (user != null)
                {
                    CResUserLogin ret = new CResUserLogin();
                    ret.UserID = user.ID;
                    ret.Address = user.Address;
                    ret.Contact = user.Contact;
                    ret.FirstName = user.FirstName;
                    ret.EmailID = user.UserMaster.EmailID;
                    if (user.DOB != null)
                        ret.DOB = user.DOB.Value.ToString("MM-dd-yyyy");
                    if (user.JoinDate != null)
                        ret.JoinDate = user.JoinDate.Value.ToString("MM-dd-yyyy");
                    ret.LastName = user.LastName;
                    if (!string.IsNullOrEmpty(user.ProfilePic))
                        ret.ProfilePic = BaseService.GetURL() + user.ProfilePic;
                    ret.Gender = user.Gender.ToLower();

                    obj.ReturnCode = ResponseMessages.SuccessCode;
                    obj.ReturnMsg = ResponseMessages.SuccessMsg;
                    obj.Data = ret;
                }
                else
                {
                    obj.ReturnCode = ResponseMessages.NoDataCode;
                    obj.ReturnMsg = "User does not exist";

                    return obj;
                }
            }
            catch (Exception)
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

        public GenericClass GetPlayerList(int UserID, string Name, int IsCheckInTeam)
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
                                   where (string.IsNullOrEmpty(Name) ? true : (t.FirstName.Contains(Name) || t.LastName.Contains(Name))) && (IsCheckInTeam == 0 ? true : t.TeamMembers.Count() == 0)
                                   select new
                                   {
                                       PlayerID = t.ID,
                                       Name = t.FirstName + " " + t.LastName,
                                       Photo = string.IsNullOrEmpty(t.ProfilePic) ? "" : BaseService.GetURL() + t.ProfilePic

                                   });
                        if (ret.Count() > 0)
                        {
                            obj.Data = ret;
                            obj.ReturnCode = ResponseMessages.SuccessCode;
                            obj.ReturnMsg = ResponseMessages.SuccessMsg;
                        }
                        else
                        {
                            obj.ReturnCode = ResponseMessages.NoDataCode;
                            obj.ReturnMsg = ResponseMessages.NoDataMsg;
                            obj.Data = new List<int>();
                            return obj;
                        }
                    }
                    else if (user.RoleID == 3)
                    {
                        var ret = (from t in DC.TeamMembers.AsEnumerable()
                                   where (t.Team.TeamManagerID == user.TeamManagers.FirstOrDefault().ID) && (string.IsNullOrEmpty(Name) ? true : t.PlayerMaster.FirstName.Contains(Name) || t.PlayerMaster.LastName.Contains(Name)) && (IsCheckInTeam == 0 ? true : t.PlayerMaster.TeamMembers.Count() == 0)
                                   select new
                                   {
                                       PlayerID = t.PlayerID,
                                       Name = t.PlayerMaster.FirstName + " " + t.PlayerMaster.LastName,
                                       Photo = string.IsNullOrEmpty(t.PlayerMaster.ProfilePic) ? "" : BaseService.GetURL() + t.PlayerMaster.ProfilePic

                                   });


                        if (ret.Count() > 0)
                        {
                            obj.Data = ret;
                            obj.ReturnCode = ResponseMessages.SuccessCode;
                            obj.ReturnMsg = ResponseMessages.SuccessMsg;
                        }
                        else
                        {
                            obj.ReturnCode = ResponseMessages.NoDataCode;
                            obj.ReturnMsg = ResponseMessages.NoDataMsg;
                            obj.Data = new List<int>();
                            return obj;
                        }

                    }

                }
                else
                {
                    obj.ReturnCode = ResponseMessages.NoDataCode;
                    obj.ReturnMsg = "User does not exist";
                    obj.Data = new List<int>();
                    return obj;
                }

            }
            catch (Exception EX)
            {
                throw;
            }

            return obj;
        }

        public GenericClass GetPlayerDetails(int PlayerID)
        {
            GenericClass obj = new GenericClass();
            try
            {
                var user = DC.PlayerMasters.Where(x => x.ID == PlayerID).FirstOrDefault();
                if (user != null)
                {
                    CResUserLogin ret = new CResUserLogin();
                    ret.UserID = user.ID;
                    ret.Address = user.Address;
                    ret.Contact = user.Contact;
                    ret.FirstName = user.FirstName;
                    ret.EmailID = user.UserMaster.EmailID;
                    if (user.DOB != null)
                        ret.DOB = user.DOB.Value.ToString("MM-dd-yyyy");
                    if (user.JoinDate != null)
                        ret.JoinDate = user.JoinDate.Value.ToString("MM-dd-yyyy");
                    ret.LastName = user.LastName;
                    if (!string.IsNullOrEmpty(user.ProfilePic))
                        ret.ProfilePic = BaseService.GetURL() + user.ProfilePic;
                    ret.Gender = user.Gender.ToLower();
                    ret.Experience = user.Experience == null ? 0 : user.Experience;

                    obj.ReturnCode = ResponseMessages.SuccessCode;
                    obj.ReturnMsg = ResponseMessages.SuccessMsg;
                    obj.Data = ret;
                }
                else
                {
                    obj.ReturnCode = ResponseMessages.NoDataCode;
                    obj.ReturnMsg = "User does not exist";

                    return obj;
                }
            }
            catch (Exception)
            {

                throw;
            }
            return obj;

        }

        public async void SendRegistrationEmail(string Email, string pass, string Name)
        {
            try
            {
                string Body = string.Empty;
                System.Net.Mail.MailMessage objMail = new System.Net.Mail.MailMessage();
                string Subject = "Registration";
                using (StreamReader reader = new StreamReader(HttpContext.Current.Server.MapPath("~/Registration.html")))

                {
                    Body = reader.ReadToEnd();
                }
                string SenderEmail = "krishna962824@gmail.com";
                string Password = "2896krish";
                Body = Body.Replace("#Pass#", pass);
                Body = Body.Replace("#Name#", Name);
                Body = Body.Replace("#Email#", Email);

                objMail.IsBodyHtml = true;
                objMail.Body = Body;
                objMail.From = new MailAddress(SenderEmail, "Volleyball App");
                objMail.To.Add(new MailAddress(Email));
                objMail.Subject = Subject;
                AlternateView htmlMail = null;
                htmlMail = AlternateView.CreateAlternateViewFromString(Body, null, MediaTypeNames.Text.Html);
                objMail.Priority = MailPriority.Normal;
                objMail.AlternateViews.Add(htmlMail);
                objMail.IsBodyHtml = true;
                SmtpClient SMTPClientObj = new SmtpClient();
                SMTPClientObj.Credentials = new System.Net.NetworkCredential(SenderEmail, Password);
                SMTPClientObj.Host = "smtp.gmail.com";
                SMTPClientObj.Port = 587;
                SMTPClientObj.EnableSsl = true;
                SMTPClientObj.Send(objMail);
            }
            catch { }

        }

        public GenericClass GetTeamManagerList(int UserID, string Search)
        {
            GenericClass obj = new GenericClass();

            try
            {
                UserMaster user = (from u in DC.UserMasters
                                   where u.ID == UserID
                                   select u).FirstOrDefault();
                if (user != null)
                {
                    var ret = (from t in DC.TeamManagers.AsEnumerable()
                               where (string.IsNullOrEmpty(Search) ? true : t.FirstName.Contains(Search) || t.LastName.Contains(Search)) && t.Teams.Count() == 0
                               select new
                               {
                                   TeamManagerID = t.ID,
                                   Name = t.FirstName + " " + t.LastName,
                                   Photo = string.IsNullOrEmpty(t.ProfilePic) ? "" : BaseService.GetURL() + t.ProfilePic

                               });
                    if (ret.Count() > 0)
                    {
                        obj.Data = ret;
                        obj.ReturnCode = ResponseMessages.SuccessCode;
                        obj.ReturnMsg = ResponseMessages.SuccessMsg;
                    }
                    else
                    {
                        obj.ReturnCode = ResponseMessages.NoDataCode;
                        obj.ReturnMsg = ResponseMessages.NoDataMsg;
                        obj.Data = new List<int>();
                        return obj;
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

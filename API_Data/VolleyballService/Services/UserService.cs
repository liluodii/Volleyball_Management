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

namespace VolleyballService.Services
{
    public class UserService : CConnect
    {
        public GenericClass Login(CReqUserLogin Data)
        {
            GenericClass obj = new GenericClass();

            try
            {
                CResUserLogin resData = new CResUserLogin();


                UserMaster user = (from u in DC.UserMasters
                                   where u.EmailID == Data.EmailID
                                   select u).FirstOrDefault();

                if (user != null)
                {
                    if (user.Password == Data.Password)
                    {
                        if (user.RoleID == 2)
                        {
                            resData.EmailID = user.EmailID;
                            resData.Contact = user.LeagueManagers.FirstOrDefault()?.Contact;
                            resData.FirstName = user.LeagueManagers.FirstOrDefault()?.FirstName;
                            resData.LastName = user.LeagueManagers.FirstOrDefault()?.LastName;
                            resData.Gender = user.LeagueManagers.FirstOrDefault()?.Gender;
                            resData.Contact = user.LeagueManagers.FirstOrDefault()?.Contact;
                            if (user.LeagueManagers.FirstOrDefault()?.JoinDate.HasValue == true)
                                resData.JoinDate = user.LeagueManagers.FirstOrDefault()?.JoinDate.Value.ToString("MM-dd-yyyy");
                            if (user.LeagueManagers.FirstOrDefault()?.DOB.HasValue == true)
                                resData.DOB = user.LeagueManagers.FirstOrDefault()?.DOB.Value.ToString("MM-dd-yyyy");
                            resData.UserID = user.ID;
                            resData.Address = user.LeagueManagers.FirstOrDefault()?.Address;
                            string profilePic = user.LeagueManagers.FirstOrDefault()?.ProfilePic;
                            resData.ProfilePic = string.IsNullOrEmpty(profilePic) ? "" : BaseService.GetURL() + profilePic; ;

                            resData.RoleID = user.RoleID;
                            resData.RoleName = user.RoleMaster.Name;
                        }
                        else if (user.RoleID == 3)
                        {
                            resData.EmailID = user.EmailID;
                            resData.Contact = user.TeamManagers.FirstOrDefault()?.Contact;
                            resData.FirstName = user.TeamManagers.FirstOrDefault()?.FirstName;
                            resData.LastName = user.TeamManagers.FirstOrDefault()?.LastName;
                            resData.Gender = user.TeamManagers.FirstOrDefault()?.Gender;
                            resData.Contact = user.TeamManagers.FirstOrDefault()?.Contact;
                            resData.UserID = user.ID;
                            if (user.TeamManagers.FirstOrDefault()?.JoinDate.HasValue == true)
                                resData.JoinDate = user.TeamManagers.FirstOrDefault()?.JoinDate.Value.ToString("MM-dd-yyyy");

                            if (user.TeamManagers.FirstOrDefault()?.DOB.HasValue == true)
                                resData.DOB = user.TeamManagers.FirstOrDefault()?.DOB.Value.ToString("MM-dd-yyyy"); 
                            resData.Address = user.TeamManagers.FirstOrDefault()?.Address;
                            string profilePic = user.TeamManagers.FirstOrDefault()?.ProfilePic;
                            resData.ProfilePic = string.IsNullOrEmpty(profilePic) ? "" : BaseService.GetURL() + profilePic;

                            resData.RoleID = user.RoleID;
                            resData.RoleName = user.RoleMaster.Name;
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
                        obj.ReturnMsg = "Password does not match";
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
                obj.ReturnMsg = ResponseMessages.SuccessMsg;
                obj.Data = resData;

            }
            catch (Exception EX)
            {

                throw;
            }

            return obj;

        }


        public GenericClass ChangePassword(CReqChangePass Data)
        {
            GenericClass obj = new GenericClass();

            try
            {
                UserMaster user = (from u in DC.UserMasters
                                   where u.ID == Data.UserID
                                   select u).FirstOrDefault();

                if (user != null)
                {

                    if (user.Password == Data.Password)
                    {
                        obj.ReturnCode = ResponseMessages.NoDataCode;
                        obj.ReturnMsg = "Current password and updated password are same.";
                        return obj;
                    }

                    else
                    {
                        user.Password = Data.Password;
                        DC.SaveChanges();

                        obj.ReturnCode = ResponseMessages.SuccessCode;
                        obj.ReturnMsg = "Password changed successfully.";

                    }
                }
                else
                {
                    obj.ReturnCode = ResponseMessages.NoDataCode;
                    obj.ReturnMsg = "User does not exist";
                    return obj;
                }

                return obj;


            }
            catch
            {
                throw;
            }


        }


        public GenericClass UpdateProfile(CReqUpdateProfile Data)
        {
            GenericClass obj = new GenericClass();
            CResUserLogin resData = new CResUserLogin();
            try
            {
                UserMaster user = (from u in DC.UserMasters
                                   where u.ID == Data.UserID
                                   select u).FirstOrDefault();

                if (user != null)
                {
                    if (user.RoleID == 2)
                    {
                        LeagueManager reqData = user.LeagueManagers.FirstOrDefault();
                        if (reqData == null)
                        {

                            obj.ReturnCode = ResponseMessages.NoDataCode;
                            obj.ReturnMsg = "User does not exist";
                            return obj;
                        }
                        else
                        {
                            reqData.Address = Data.Address;
                            reqData.Contact = Data.Contact;
                            reqData.DOB = Convert.ToDateTime(Data.DOB);
                            reqData.FirstName = Data.FirstName;
                            reqData.LastName = Data.LastName;
                            reqData.JoinDate = Convert.ToDateTime(Data.JoinDate);
                            reqData.Gender = Data.Gender;
                            DC.SaveChanges();

                            resData.EmailID = user.EmailID;
                            resData.Contact = user.LeagueManagers.FirstOrDefault()?.Contact;
                            resData.FirstName = user.LeagueManagers.FirstOrDefault()?.FirstName;
                            resData.LastName = user.LeagueManagers.FirstOrDefault()?.LastName;
                            resData.Gender = user.LeagueManagers.FirstOrDefault()?.Gender;
                            resData.Contact = user.LeagueManagers.FirstOrDefault()?.Contact;


                            if (user.LeagueManagers.FirstOrDefault()?.JoinDate.HasValue == true)
                                resData.JoinDate = user.LeagueManagers.FirstOrDefault()?.JoinDate.Value.ToString("MM-dd-yyyy");

                            if (user.LeagueManagers.FirstOrDefault()?.DOB.HasValue == true)
                                resData.DOB = user.LeagueManagers.FirstOrDefault()?.DOB.Value.ToString("MM-dd-yyyy");



                            resData.UserID = user.ID;
                            resData.Address = user.LeagueManagers.FirstOrDefault()?.Address;
                            string profilePic = user.LeagueManagers.FirstOrDefault()?.ProfilePic;
                            resData.ProfilePic = string.IsNullOrEmpty(profilePic) ? "" : BaseService.GetURL() + profilePic;

                            resData.RoleID = user.RoleID;
                            resData.RoleName = user.RoleMaster.Name;
                        }

                    }
                    else if (user.RoleID == 3)
                    {

                        TeamManager reqData = user.TeamManagers.FirstOrDefault();
                        if (reqData == null)
                        {

                            obj.ReturnCode = ResponseMessages.NoDataCode;
                            obj.ReturnMsg = "User does not exist";
                            return obj;
                        }
                        else
                        {
                            reqData.Address = Data.Address;
                            reqData.Contact = Data.Contact;
                            reqData.DOB = Convert.ToDateTime(Data.DOB);
                            reqData.FirstName = Data.FirstName;
                            reqData.LastName = Data.LastName;
                            reqData.JoinDate = Convert.ToDateTime(Data.JoinDate);
                            reqData.Gender = Data.Gender;
                            DC.SaveChanges();


                            resData.EmailID = user.EmailID;
                            resData.Contact = user.TeamManagers.FirstOrDefault()?.Contact;
                            resData.FirstName = user.TeamManagers.FirstOrDefault()?.FirstName;
                            resData.LastName = user.TeamManagers.FirstOrDefault()?.LastName;
                            resData.Gender = user.TeamManagers.FirstOrDefault()?.Gender;
                            resData.Contact = user.TeamManagers.FirstOrDefault()?.Contact;
                            resData.UserID = user.ID;
                            if (user.TeamManagers.FirstOrDefault()?.JoinDate.HasValue == true)
                                resData.JoinDate = user.TeamManagers.FirstOrDefault()?.JoinDate.ToString();

                            if (user.TeamManagers.FirstOrDefault()?.DOB.HasValue == true)
                                resData.DOB = user.TeamManagers.FirstOrDefault()?.DOB.ToString();
                            resData.Address = user.TeamManagers.FirstOrDefault()?.Address;
                            string profilePic = user.TeamManagers.FirstOrDefault()?.ProfilePic;
                            resData.ProfilePic = string.IsNullOrEmpty(profilePic) ? "" : BaseService.GetURL() + profilePic;

                            resData.RoleID = user.RoleID;
                            resData.RoleName = user.RoleMaster.Name;

                        }
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
                obj.ReturnMsg = "Profile update successfully.";
                obj.Data = resData;

            }
            catch (Exception EX)
            {

                throw;
            }

            return obj;
        }

        public GenericClass ForgetPassword(CReqForgetPassword Data)
        {
            GenericClass obj = new GenericClass();

            try
            {
                UserMaster user = (from u in DC.UserMasters
                                   where u.EmailID.ToLower() == Data.Email.ToLower()
                                   select u).FirstOrDefault();

                if (user != null)
                {
                    string Body = string.Empty;
                    System.Net.Mail.MailMessage objMail = new System.Net.Mail.MailMessage();
                    string Subject = "Password Recovery";
                    using (StreamReader reader = new StreamReader(HttpContext.Current.Server.MapPath("~/Fogotpassword.html")))

                    {
                        Body = reader.ReadToEnd();
                    }
                    string SenderEmail = "krishna962824@gmail.com";
                    string Password = "2896krish";
                    Body = Body.Replace("#Pass#", user.Password);

                    if (user.RoleID == 2)
                        Body = Body.Replace("#Name#", user.LeagueManagers.FirstOrDefault().FirstName + " " + user.LeagueManagers.FirstOrDefault().LastName);

                    else if (user.RoleID == 3)
                        Body = Body.Replace("#Name#", user.TeamManagers.FirstOrDefault().FirstName + " " + user.TeamManagers.FirstOrDefault().LastName);




                    objMail.IsBodyHtml = true;
                    objMail.Body = Body;

                    objMail.From = new MailAddress(SenderEmail, "Volleyball App");
                    objMail.To.Add(new MailAddress(user.EmailID));
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

                    obj.ReturnCode = ResponseMessages.SuccessCode;
                    obj.ReturnMsg = "Please check your email and you will get password on registered emailid.";

                }
                else
                {
                    obj.ReturnCode = ResponseMessages.NoDataCode;
                    obj.ReturnMsg = "User does not exist.";
                }
            }
            catch (Exception)
            {
                throw;
            }

            return obj;
        }


        public GenericClass AddUserProfilePic(int UserID, HttpPostedFile File1)
        {
            GenericClass obj = new GenericClass();
            try
            {
                string file = "";
                if (File1 != null)
                {
                    UserMaster UD = (from u in DC.UserMasters
                                     where u.ID == UserID
                                     select u).FirstOrDefault();

                    if (UD != null)
                    {
                        if (UD.RoleID == 2)
                        {
                            if (!Directory.Exists("/Images/User"))
                                Directory.CreateDirectory(HttpContext.Current.Server.MapPath("~/Images/User/"));

                            if (!Directory.Exists("/Images/User/" + UserID.ToString()))
                                Directory.CreateDirectory(HttpContext.Current.Server.MapPath("~/Images/User/" + UserID.ToString()));

                            file = "Images/User/" + UserID.ToString() + "/" + Guid.NewGuid().ToString() + ".png";
                            string fileLocation = HttpContext.Current.Server.MapPath("~/" + file);
                            File1.SaveAs(fileLocation);

                            UD.LeagueManagers.FirstOrDefault().ProfilePic = file;
                        }
                        else if (UD.RoleID == 3)
                        {

                            if (!Directory.Exists("/Images/Provider"))
                                Directory.CreateDirectory(HttpContext.Current.Server.MapPath("~/Images/Provider/"));

                            if (!Directory.Exists("/Images/Provider/" + UserID.ToString()))
                                Directory.CreateDirectory(HttpContext.Current.Server.MapPath("~/Images/Provider/" + UserID.ToString()));

                            file = "Images/Provider/" + UserID.ToString() + "/" + Guid.NewGuid().ToString() + ".png";
                            string fileLocation = HttpContext.Current.Server.MapPath("~/" + file);
                            File1.SaveAs(fileLocation);

                            UD.TeamManagers.FirstOrDefault().ProfilePic = file;
                        }
                        else
                        {
                            obj.ReturnCode = ResponseMessages.NoDataCode;
                            obj.ReturnMsg = "User doesnot found.";

                        }

                        DC.SaveChanges();
                        obj.ReturnCode = ResponseMessages.SuccessCode;
                        obj.ReturnMsg = "photo updated.";
                        obj.ReturnValue = BaseService.GetURL() + file;
                    }
                    else
                    {
                        obj.ReturnCode = ResponseMessages.NoDataCode;
                        obj.ReturnMsg = "User doesnot found.";
                    }
                }
                else
                {
                    obj.ReturnCode = ResponseMessages.NoDataCode;
                    obj.ReturnMsg = ResponseMessages.NoDataMsg;
                }
            }
            catch (Exception)
            {

                throw;
            }
            return obj;
        }


    }


}

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

                            resData.JoinDate = user.LeagueManagers.FirstOrDefault()?.JoinDate.ToString();
                            resData.DOB = user.LeagueManagers.FirstOrDefault()?.DOB.ToString();

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

                            resData.JoinDate = user.TeamManagers.FirstOrDefault()?.JoinDate.ToString();
                            resData.DOB = user.TeamManagers.FirstOrDefault()?.DOB.ToString();

                            string profilePic = user.TeamManagers.FirstOrDefault()?.ProfilePic;
                            resData.ProfilePic = string.IsNullOrEmpty(profilePic) ? "": BaseService.GetURL()+profilePic;

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


    }


}

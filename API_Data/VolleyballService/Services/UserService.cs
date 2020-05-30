using System;
using System.Linq;
using System.Web;
using System.Data;
using System.Collections.Generic;
using static Volleyball.EF.CustomClasses.CRequest;
using static Volleyball.EF.CustomClasses.CResponse;
using Volleyball.EF;
using Volleyball.Common;

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
                        resData.EmailID = user.EmailID;
                        resData.Contact = user.PlayerMasters.FirstOrDefault()?.Contact;
                        resData.FirstName = user.PlayerMasters.FirstOrDefault()?.FirstName;
                        resData.LastName = user.PlayerMasters.FirstOrDefault()?.LastName;
                        resData.Gender = user.PlayerMasters.FirstOrDefault()?.Gender;
                        resData.Contact = user.PlayerMasters.FirstOrDefault()?.Contact;

                        resData.JoinDate = user.PlayerMasters.FirstOrDefault()?.JoinDate.ToString();
                        resData.DOB = user.PlayerMasters.FirstOrDefault()?.DOB.ToString();
                        resData.Experience = user.PlayerMasters.FirstOrDefault()?.Experience;
                        string profilePic = user.PlayerMasters.FirstOrDefault()?.ProfilePic;
                        resData.ProfilePic = string.IsNullOrEmpty(profilePic) ? "" : profilePic;

                        resData.RoleID = user.RoleID;
                        resData.RoleName = user.RoleMaster.Name;
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

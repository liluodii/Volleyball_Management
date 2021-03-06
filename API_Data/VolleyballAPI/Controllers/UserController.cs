﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Http;

using Volleyball.Common;

using VolleyballService.Services;

using static Volleyball.EF.CustomClasses.CRequest;
using static Volleyball.EF.CustomClasses.CResponse;

namespace VolleyballAPI.Controllers
{
    public class UserController : ApiController
    {
        public BaseService _BaseService = new BaseService();
        UserService _Service = new UserService();

        [Route("Login")]
        [HttpPost]
        public GenericClass Login(CReqUserLogin Data)
        {
            GenericClass Obj = new GenericClass();
            try
            {
                if (Data == null)
                {

                    Obj.ReturnCode = ResponseMessages.NoDataCode;
                    Obj.ReturnMsg = "Please enter valid data";
                    Obj.ReturnValue = string.Empty;
                    return Obj;
                }



                if (Data.APIKey == BaseService.TOKEN)
                {
                    Obj = _Service.Login(Data);

                    string JData = _BaseService.ConvertJsontoString(Data);
                    string JData1 = _BaseService.ConvertJsontoString(Obj);

                    _BaseService.ValidateAPIToken(Data.APIKey, JData, ActionContext.ActionDescriptor.ActionName, JData1);

                }

                else
                {
                    Obj.ReturnCode = ResponseMessages.AuthenticationFailedCode;
                    Obj.ReturnMsg = ResponseMessages.AuthenticationFailedMsg;
                    Obj.ReturnValue = string.Empty;

                }
            }
#pragma warning disable CS0168 // The variable 'ex' is declared but never used
            catch
#pragma warning restore CS0168 // The variable 'ex' is declared but never used
            {
                Obj.ReturnCode = ResponseMessages.ErrorCode;
                Obj.ReturnMsg = ResponseMessages.ErrorMsg;
                Obj.ReturnValue = string.Empty;
            }
            return Obj;
        }


        [Route("ChangePassword")]
        [HttpPost]
        public GenericClass ChangePassword(CReqChangePass Data)
        {
            GenericClass Obj = new GenericClass();
            try
            {
                if (Data == null)
                {

                    Obj.ReturnCode = ResponseMessages.NoDataCode;
                    Obj.ReturnMsg = "Please enter valid data";
                    Obj.ReturnValue = string.Empty;
                    return Obj;
                }


                if (Data.APIKey == BaseService.TOKEN)
                {
                    Obj = _Service.ChangePassword(Data);

                    string JData = _BaseService.ConvertJsontoString(Data);
                    string JData1 = _BaseService.ConvertJsontoString(Obj);

                    _BaseService.ValidateAPIToken(Data.APIKey, JData, ActionContext.ActionDescriptor.ActionName, JData1);

                }
                else
                {
                    Obj.ReturnCode = ResponseMessages.AuthenticationFailedCode;
                    Obj.ReturnMsg = ResponseMessages.AuthenticationFailedMsg;
                    Obj.ReturnValue = string.Empty;

                }
            }
#pragma warning disable CS0168 // The variable 'ex' is declared but never used
            catch
#pragma warning restore CS0168 // The variable 'ex' is declared but never used
            {
                Obj.ReturnCode = ResponseMessages.ErrorCode;
                Obj.ReturnMsg = ResponseMessages.ErrorMsg;
                Obj.ReturnValue = string.Empty;
            }
            return Obj;
        }


        [Route("UpdateProfile")]
        [HttpPost]
        public GenericClass UpdateProfile(CReqUpdateProfile Data)
        {
            GenericClass Obj = new GenericClass();
            try
            {
                if (Data == null)
                {

                    Obj.ReturnCode = ResponseMessages.NoDataCode;
                    Obj.ReturnMsg = "Please enter valid data";
                    Obj.ReturnValue = string.Empty;
                    return Obj;
                }


                if (Data.APIKey == BaseService.TOKEN)
                {
                    Obj = _Service.UpdateProfile(Data);

                    string JData = _BaseService.ConvertJsontoString(Data);
                    string JData1 = _BaseService.ConvertJsontoString(Obj);

                    _BaseService.ValidateAPIToken(Data.APIKey, JData, ActionContext.ActionDescriptor.ActionName, JData1);

                }

                else
                {
                    Obj.ReturnCode = ResponseMessages.AuthenticationFailedCode;
                    Obj.ReturnMsg = ResponseMessages.AuthenticationFailedMsg;
                    Obj.ReturnValue = string.Empty;

                }
            }
#pragma warning disable CS0168 // The variable 'ex' is declared but never used
            catch
#pragma warning restore CS0168 // The variable 'ex' is declared but never used
            {
                Obj.ReturnCode = ResponseMessages.ErrorCode;
                Obj.ReturnMsg = ResponseMessages.ErrorMsg;
                Obj.ReturnValue = string.Empty;
            }
            return Obj;
        }


        [Route("ForgetPassword")]
        [HttpPost]
        public GenericClass ForgetPassword(CReqForgetPassword Data)
        {
            GenericClass Obj = new GenericClass();
            try
            {
                if (Data == null)
                {

                    Obj.ReturnCode = ResponseMessages.NoDataCode;
                    Obj.ReturnMsg = "Please enter valid data";
                    Obj.ReturnValue = string.Empty;
                    return Obj;
                }


                if (Data.APIKey == BaseService.TOKEN)
                {
                    Obj = _Service.ForgetPassword(Data);

                    string JData = _BaseService.ConvertJsontoString(Data);
                    string JData1 = _BaseService.ConvertJsontoString(Obj);

                    _BaseService.ValidateAPIToken(Data.APIKey, JData, ActionContext.ActionDescriptor.ActionName, JData1);

                }

                else
                {
                    Obj.ReturnCode = ResponseMessages.AuthenticationFailedCode;
                    Obj.ReturnMsg = ResponseMessages.AuthenticationFailedMsg;
                    Obj.ReturnValue = string.Empty;

                }
            }
#pragma warning disable CS0168 // The variable 'ex' is declared but never used
            catch
#pragma warning restore CS0168 // The variable 'ex' is declared but never used
            {
                Obj.ReturnCode = ResponseMessages.ErrorCode;
                Obj.ReturnMsg = ResponseMessages.ErrorMsg;
                Obj.ReturnValue = string.Empty;
            }
            return Obj;
        }



        [HttpPost]
        [Route("AddUserProfilePic")]
        public GenericClass AddUserProfilePic()
        {
            GenericClass obj = new GenericClass();
            try
            {
                string APIKey = HttpContext.Current.Request.Form["APIKey"].ToString();
                int UserID = Convert.ToInt32(HttpContext.Current.Request.Form["UserID"].ToString());

                bool _IsValidToken = _BaseService.ValidateAPIToken(APIKey);
                if (_IsValidToken == true)
                {
                    HttpPostedFile ImageData = HttpContext.Current.Request.Files["ImageData"];
                    obj = _Service.AddUserProfilePic(UserID, ImageData);
                }
                else
                {
                    obj.ReturnCode = ResponseMessages.AuthenticationFailedCode;
                    obj.ReturnMsg = ResponseMessages.AuthenticationFailedMsg;
                    obj.ReturnValue = string.Empty;
                }
            }
            catch (Exception ex)
            {

                obj.ReturnCode = ResponseMessages.ErrorCode;
                obj.ReturnMsg = ResponseMessages.ErrorMsg;
                obj.ReturnValue = string.Empty;
            }
            return obj;

        }

        [HttpPost]
        [Route("AddManageTeam")]
        public GenericClass AddManageTeam()
        {
            GenericClass obj = new GenericClass();
            try
            {
                string APIKey = HttpContext.Current.Request.Form["APIKey"].ToString();
                int UserID = Convert.ToInt32(HttpContext.Current.Request.Form["UserID"].ToString());
                int TeamID = Convert.ToInt32(HttpContext.Current.Request.Form["TeamID"].ToString());
                int TeamManagerID = Convert.ToInt32(HttpContext.Current.Request.Form["TeamManagerID"].ToString());

                string Name = HttpContext.Current.Request.Form["Name"].ToString();

                bool _IsValidToken = _BaseService.ValidateAPIToken(APIKey);
                if (_IsValidToken == true)
                {
                    HttpPostedFile ImageData = HttpContext.Current.Request.Files["ImageData"];
                    obj = _Service.AddManageTeam(UserID, ImageData, Name, TeamManagerID, TeamID);
                }
                else
                {
                    obj.ReturnCode = ResponseMessages.AuthenticationFailedCode;
                    obj.ReturnMsg = ResponseMessages.AuthenticationFailedMsg;
                    obj.ReturnValue = string.Empty;
                }
            }
            catch (Exception ex)
            {

                obj.ReturnCode = ResponseMessages.ErrorCode;
                obj.ReturnMsg = ResponseMessages.ErrorMsg;
                obj.ReturnValue = string.Empty;
            }
            return obj;

        }


        [Route("DeleteTeam")]
        [HttpDelete]
        public GenericClass DeleteTeam(CReqDeleteTeam Data)
        {
            GenericClass Obj = new GenericClass();
            try
            {
                if (Data == null)
                {

                    Obj.ReturnCode = ResponseMessages.NoDataCode;
                    Obj.ReturnMsg = "Please enter valid data";
                    Obj.ReturnValue = string.Empty;
                    return Obj;
                }


                if (Data.APIKey == BaseService.TOKEN)
                {
                    Obj = _Service.DeleteTeam(Data);

                    string JData = _BaseService.ConvertJsontoString(Data);
                    string JData1 = _BaseService.ConvertJsontoString(Obj);

                    _BaseService.ValidateAPIToken(Data.APIKey, JData, ActionContext.ActionDescriptor.ActionName, JData1);

                }


                else
                {
                    Obj.ReturnCode = ResponseMessages.AuthenticationFailedCode;
                    Obj.ReturnMsg = ResponseMessages.AuthenticationFailedMsg;
                    Obj.ReturnValue = string.Empty;

                }
            }
#pragma warning disable CS0168 // The variable 'ex' is declared but never used
            catch
#pragma warning restore CS0168 // The variable 'ex' is declared but never used
            {
                Obj.ReturnCode = ResponseMessages.ErrorCode;
                Obj.ReturnMsg = ResponseMessages.ErrorMsg;
                Obj.ReturnValue = string.Empty;
            }
            return Obj;
        }

        [Route("GetTeamList")]
        [HttpGet]
        public GenericClass GetTeamList(int UserID = 0, string Search = "")
        {
            GenericClass Obj = new GenericClass();
            try
            {
                if (UserID == 0)
                {

                    Obj.ReturnCode = ResponseMessages.NoDataCode;
                    Obj.ReturnMsg = "Please enter valid data";
                    Obj.ReturnValue = string.Empty;
                    return Obj;
                }

                Obj = _Service.GetTeamList(UserID, Search);

                string JData1 = _BaseService.ConvertJsontoString(Obj);

                _BaseService.ValidateAPIToken("123", "", ActionContext.ActionDescriptor.ActionName, JData1);




            }
#pragma warning disable CS0168 // The variable 'ex' is declared but never used
            catch
#pragma warning restore CS0168 // The variable 'ex' is declared but never used
            {
                Obj.ReturnCode = ResponseMessages.ErrorCode;
                Obj.ReturnMsg = ResponseMessages.ErrorMsg;
                Obj.ReturnValue = string.Empty;
                Obj.Data = new List<int>();

            }
            return Obj;
        }

        [Route("AddPlayerInTeam")]
        [HttpPut]
        public GenericClass AddPlayerInTeam(CReqAddTeamMemberInTeam Data)
        {
            GenericClass Obj = new GenericClass();
            try
            {
                if (Data == null)
                {

                    Obj.ReturnCode = ResponseMessages.NoDataCode;
                    Obj.ReturnMsg = "Please enter valid data";
                    Obj.ReturnValue = string.Empty;
                    return Obj;
                }


                if (Data.APIKey == BaseService.TOKEN)
                {
                    Obj = _Service.AddPlayerInTeam(Data);

                    string JData = _BaseService.ConvertJsontoString(Data);
                    string JData1 = _BaseService.ConvertJsontoString(Obj);

                    _BaseService.ValidateAPIToken(Data.APIKey, JData, ActionContext.ActionDescriptor.ActionName, JData1);

                }
                else
                {
                    Obj.ReturnCode = ResponseMessages.AuthenticationFailedCode;
                    Obj.ReturnMsg = ResponseMessages.AuthenticationFailedMsg;
                    Obj.ReturnValue = string.Empty;

                }
            }
#pragma warning disable CS0168 // The variable 'ex' is declared but never used
            catch
#pragma warning restore CS0168 // The variable 'ex' is declared but never used
            {
                Obj.ReturnCode = ResponseMessages.ErrorCode;
                Obj.ReturnMsg = ResponseMessages.ErrorMsg;
                Obj.ReturnValue = string.Empty;
            }
            return Obj;
        }

        [Route("DeletePlayerFromTeam")]
        [HttpDelete]
        public GenericClass DeletePlayerFromTeam(CReqDeletePlayerFromTeam Data)
        {
            GenericClass Obj = new GenericClass();
            try
            {
                if (Data == null)
                {

                    Obj.ReturnCode = ResponseMessages.NoDataCode;
                    Obj.ReturnMsg = "Please enter valid data";
                    Obj.ReturnValue = string.Empty;
                    return Obj;
                }



                if (Data.APIKey == BaseService.TOKEN)
                {
                    Obj = _Service.DeletePlayerFromTeam(Data);

                    string JData = _BaseService.ConvertJsontoString(Data);
                    string JData1 = _BaseService.ConvertJsontoString(Obj);

                    _BaseService.ValidateAPIToken(Data.APIKey, JData, ActionContext.ActionDescriptor.ActionName, JData1);

                }
                else
                {
                    Obj.ReturnCode = ResponseMessages.AuthenticationFailedCode;
                    Obj.ReturnMsg = ResponseMessages.AuthenticationFailedMsg;
                    Obj.ReturnValue = string.Empty;

                }
            }
#pragma warning disable CS0168 // The variable 'ex' is declared but never used
            catch
#pragma warning restore CS0168 // The variable 'ex' is declared but never used
            {
                Obj.ReturnCode = ResponseMessages.ErrorCode;
                Obj.ReturnMsg = ResponseMessages.ErrorMsg;
                Obj.ReturnValue = string.Empty;
            }
            return Obj;
        }


        [Route("GetTeamMember")]
        [HttpPost]
        public GenericClass GetTeamMember(CReqDeleteTeam Data)
        {
            GenericClass Obj = new GenericClass();
            try
            {
                if (Data == null)
                {

                    Obj.ReturnCode = ResponseMessages.NoDataCode;
                    Obj.ReturnMsg = "Please enter valid data";
                    Obj.ReturnValue = string.Empty;
                    return Obj;
                }


                if (Data.APIKey == BaseService.TOKEN)
                {
                    Obj = _Service.GetTeamMember(Data);

                    string JData = _BaseService.ConvertJsontoString(Data);
                    string JData1 = _BaseService.ConvertJsontoString(Obj);

                    _BaseService.ValidateAPIToken(Data.APIKey, JData, ActionContext.ActionDescriptor.ActionName, JData1);

                }

                else
                {
                    Obj.ReturnCode = ResponseMessages.AuthenticationFailedCode;
                    Obj.ReturnMsg = ResponseMessages.AuthenticationFailedMsg;
                    Obj.ReturnValue = string.Empty;

                }
            }
#pragma warning disable CS0168 // The variable 'ex' is declared but never used
            catch
#pragma warning restore CS0168 // The variable 'ex' is declared but never used
            {
                Obj.ReturnCode = ResponseMessages.ErrorCode;
                Obj.ReturnMsg = ResponseMessages.ErrorMsg;
                Obj.ReturnValue = string.Empty;
            }
            return Obj;
        }


    }
}
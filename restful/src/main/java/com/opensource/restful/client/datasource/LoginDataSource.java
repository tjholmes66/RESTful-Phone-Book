package com.opensource.restful.client.datasource;

import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.http.client.URL;
import com.opensource.restful.shared.Constants;
import com.opensource.restful.shared.JSOMapping;
import com.opensource.restful.shared.dto.UserDTO;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.util.JSON;

public class LoginDataSource extends AbstractRestDataSource
{
    private static LoginDataSource instance = null;

    public static LoginDataSource getInstance()
    {
        if (instance == null)
        {
            instance = new LoginDataSource("restLoginDS");
        }
        return instance;
    }

    private LoginDataSource(String id)
    {
        super(id);
    }

    protected void init()
    {
        System.out.println("init: START");

        setDataFormat(DSDataFormat.JSON);
        setJsonRecordXPath("/");

        // set the values for the datasource
        DataSourceTextField Id = new DataSourceTextField(Constants.USER_ID, Constants.TITLE_USER_ID);
        Id.setPrimaryKey(true);
        Id.setCanEdit(false);

        DataSourceTextField username = new DataSourceTextField(Constants.USER_USERNAME, Constants.TITLE_USER_USERNAME);
        username.setCanEdit(false);

        DataSourceTextField password = new DataSourceTextField(Constants.USER_PASSWORD, Constants.TITLE_USER_PASSWORD);
        password.setCanEdit(false);

        System.out.println("init: FINISH");
        setFields(Id, username, password);
    }

    @Override
    protected String getServiceRoot()
    {
        return "rest/login/";
    }

    @Override
    protected String getPrimaryKeyProperty()
    {
        return "username";
    }

    /*
     * Implementers can override this method to create a different override.
     */
    @SuppressWarnings("rawtypes")
    protected void postProcessTransform(DSRequest request)
    {
        System.out.println("LoginDataSource: postProcessTransform: START");

        StringBuilder url = new StringBuilder(getServiceRoot());
        System.out.println("LoginDataSource: postProcessTransform: url=" + url);

        Map dataMap = request.getAttributeAsMap("data");
        System.out.println("LoginDataSource: postProcessTransform: dataMap=" + dataMap.toString());
        if (request.getOperationType() == DSOperationType.FETCH && dataMap.size() > 0)
        {
            if (dataMap.get(Constants.USER_USERNAME) != null && dataMap.get(Constants.USER_PASSWORD) != null)
            {
                url.append("user/" + dataMap.get(Constants.USER_USERNAME));
                url.append("/pwd/" + dataMap.get(Constants.USER_PASSWORD));
            }
            else if (dataMap.get(Constants.USER_USERNAME) != null && dataMap.get(Constants.USER_PASSWORD) == null)
            {
                url.append("user/" + dataMap.get(Constants.USER_USERNAME));
                url.append("/pwd/" + dataMap.get(Constants.USER_PASSWORD));
            }
            else if (dataMap.get(Constants.USER_EMAIL) != null)
            {
                url.append("email/" + dataMap.get(Constants.USER_EMAIL));
            }
        }

        System.out.println("LoginDataSource: postProcessTransform: url=" + url.toString());
        request.setActionURL(URL.encode(url.toString()));
    }

    @Override
    protected void transformResponse(DSResponse response, DSRequest request, Object jsonData)
    {
        System.out.println("LoginDataSource: transformResponse: START");
        JavaScriptObject jsObj = (JavaScriptObject) jsonData;
        String jsoText1 = JSON.encode(jsObj);
        System.out.println("LoginDataSource: transformResponse: START: jsoText=" + jsoText1);

        UserDTO userDto = JSOMapping.convertUser(jsObj);
        System.out.println("LoginDataSource: transformResponse: FINISH: userDto=" + userDto);
        response.setAttribute("user", userDto);

        // =========================================================================================
        // the position id comes back as a position object in field: position
        JavaScriptObject jsoPosition = JSOHelper.getAttributeAsJavaScriptObject(jsObj, "position");
        String positionId = JSOHelper.getAttribute(jsoPosition, "id");
        // we set the userPositionId with the id of the position
        JSOHelper.setAttribute(jsObj, Constants.USER_POSITION_ID, positionId);
        // remove the object position
        JSOHelper.deleteAttribute(jsObj, "position");
        // =========================================================================================

        String jsoText2 = JSON.encode(jsObj);
        System.out.println("LoginDataSource: transformResponse: FINISH: jsoText=" + jsoText2);

        System.out.println("LoginDataSource: super.transformResponse: FINISH: jsonData=" + jsonData);
        super.transformResponse(response, request, jsonData);
    }

}

package pl.mmazur.requests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pl.mmazur.properites.ClickUpProperites;
import pl.mmazur.url.ClickUpUrl;

public class BaseRequest {

    private static RequestSpecBuilder requesBuilder;

    public static RequestSpecification requestSpec() {
        requesBuilder = new RequestSpecBuilder();
        requesBuilder.setBaseUri(ClickUpUrl.getBaseUrl());
        requesBuilder.setContentType(ContentType.JSON);
        requesBuilder.addHeader("Authorization", ClickUpProperites.getToken());
        requesBuilder.addFilter(new AllureRestAssured());
        return requesBuilder.build();
    }

    public static RequestSpecification requestSpecWithLogs() {
        requesBuilder = new RequestSpecBuilder();
        requesBuilder.setBaseUri(ClickUpUrl.getBaseUrl());
        requesBuilder.setContentType(ContentType.JSON);
        requesBuilder.addHeader("Authorization", ClickUpProperites.getToken());
        requesBuilder.addFilter(new RequestLoggingFilter());
        requesBuilder.addFilter(new ResponseLoggingFilter());
        requesBuilder.addFilter(new AllureRestAssured());

        return requesBuilder.build();
    }
}

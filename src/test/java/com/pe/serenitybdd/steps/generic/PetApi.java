package com.pe.serenitybdd.steps.generic;

import com.pe.serenitybdd.pet.Category;
import com.pe.serenitybdd.pet.PetInfo;
import com.pe.serenitybdd.pet.Tag;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.util.EnvironmentVariables;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PetApi {
    RequestSpecification request;
    EnvironmentVariables environmentVariables;

    public PetInfo createPetClass(Map<String, String> petData) {
        Category category = new Category(Integer.parseInt(petData.get("categoryId")), petData.get("categoryName"));
        List<String> photoUrls = new ArrayList<>();
        photoUrls.add(petData.get("photUrls"));
        List<Tag> tag = new ArrayList<>();
        Tag tagData = new Tag(Integer.parseInt(petData.get("tagsId")), petData.get("tagsName"));
        tag.add(tagData);
        PetInfo petInfo = new PetInfo(Integer.parseInt(petData.get("petId")), category, petData.get("petName"), photoUrls, tag, petData.get("status"));
        return petInfo;
    }

    public RequestSpecification createRequestWithContentType() {
        request = SerenityRest.given().baseUri(EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("url"))
                .contentType("application/json");
        return request;
    }

    public void comparePetInfo(PetInfo expectedPetInfo, PetInfo actualPetInfo) {
        Assert.assertEquals(expectedPetInfo.getId(), actualPetInfo.getId());
    }
}

package com.pe.serenitybdd.steps.stepsdefinitions;

import com.pe.serenitybdd.pet.PetInfo;
import com.pe.serenitybdd.steps.generic.PetApi;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;

import java.util.List;
import java.util.Map;

public class PetStepsDefinitions {

    Response res;
    public PetInfo petInfo = null;


    @Steps
    PetApi petApi;

    @Given("^que como owner, creo el request para (?:crear|actualizar) el pet en la store$")
    public void queComoOwnerCreoElRequestParaCrearElPetEnLaStore(List<Map<String, String>> listOfData) {
        Map<String, String> petData = listOfData.get(0);
        this.petInfo = petApi.createPetClass(petData);
    }

    @When("^como owner envio el request para crear el pet$")
    public void comoOwnerEnvioElRequestParaCrearElPet() {
        RequestSpecification requestSpecification = petApi.createRequestWithContentType();
        res = requestSpecification.log().all().body(this.petInfo).when().post("/pet");
        SerenityRest.then().log().all();
        String id = SerenityRest.lastResponse().jsonPath().getString("id");
        Serenity.setSessionVariable("id").to(id);
    }

    @Then("^como owner valido que se (?:creo correctamente|muestra el pet|actualizo correctamente) y con el estado (.*)$")
    public void comoOwnerValidoQueSeCreoCorrectamenteOMuestraElPetOActualizoYConElEstadoCode(int status) {
        SerenityRest.then().assertThat().statusCode(status);
        PetInfo petReponse = res.as(PetInfo.class);
        petApi.comparePetInfo(this.petInfo, petReponse);
    }

    @When("^como owner consulta a la aplicacion para obtener pet por id$")
    public void comoOwnerConsultaALaAplicacionParaObtenerPetPorId() {
        RequestSpecification requestSpecification = petApi.createRequestWithContentType();
        String idPet = Serenity.sessionVariableCalled("id");
        res = requestSpecification.given().log().all().pathParams("id", idPet).when().get("/pet/{id}");
        SerenityRest.then().log().all();
    }

    @When("^como owner envio el request para actualizar el pet$")
    public void comoOwnerEnvioElRequestParaActualizarElPet() {
        RequestSpecification request = petApi.createRequestWithContentType();
        res = request.given().log().all().body(this.petInfo).when().put("/pet");
        SerenityRest.then().log().all();
    }

    @When("^como owner realizo la eliminacion del pet por su id (.*)$")
    public void comoOwnerRealizoLaEliminacionDelPetPorSuIdIdPet(String idPet) {
        RequestSpecification request = petApi.createRequestWithContentType();
        res = request.given().log().all().pathParams("id", idPet).when()
                .delete("/pet/{id}");
        SerenityRest.then().log().all();
    }

    @Then("^como owner valido que se elimino correctamente con estado (.*)$")
    public void comoOwnerValidoQueSeEliminoCorrectamenteConEstadoCode(int status) {
        SerenityRest.then().assertThat().statusCode(status);
    }
}

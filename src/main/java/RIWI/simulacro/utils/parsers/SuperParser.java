package RIWI.simulacro.utils.parsers;


import org.springframework.beans.BeanUtils;

public class SuperParser<Entity,Response>   {

    public Class<Response> response;

    

    public SuperParser(Class<Response> response) {
        this.response = response;
    }



    public Response entityToResponse(Entity entidadForParsingResponse){
        try{
            Response responseEntity = response.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(entidadForParsingResponse, responseEntity);
            return responseEntity;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

}

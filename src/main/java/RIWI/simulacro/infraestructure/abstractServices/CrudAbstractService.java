package RIWI.simulacro.infraestructure.abstractServices;

import java.util.List;

public interface CrudAbstractService <Req, Res> {

    public List<Res> searchAll();

    

}

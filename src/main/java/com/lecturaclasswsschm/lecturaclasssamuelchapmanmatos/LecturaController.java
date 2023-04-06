package com.lecturaclasswsschm.lecturaclasssamuelchapmanmatos;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**@author Dell Samuel Chapman Matos*/

@RestController
public class LecturaController {
			
    public final LecturaArchivoSCHMService LECTURAARCHIVOSCHMSERVICE = new LecturaArchivoSCHMService();
		
	@GetMapping("/")
	public List<String> getStartReadFile() {
            return List.of("Please","Insert Route and File Name");
	}
                
        @PostMapping("archivos{archivos}")
        public ArrayList<ItemsClass> getReadClassFile(@RequestBody String rutaAndName) {
            return LECTURAARCHIVOSCHMSERVICE.getListObjClass(rutaAndName);
	}
}

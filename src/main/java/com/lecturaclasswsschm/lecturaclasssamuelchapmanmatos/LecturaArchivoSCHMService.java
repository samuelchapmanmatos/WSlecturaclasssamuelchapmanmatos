package com.lecturaclasswsschm.lecturaclasssamuelchapmanmatos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Dell Samuel Chapman Matos
 */
public class LecturaArchivoSCHMService {
		
                public String clMetodo="";
                public String clName="";
                public String clVari="";
                public String clScope="";
                public String clSignature="";
                public String clDescription="";   
                public String clConstructor="";
                public String clAtributo="";
                public String clMParams="";
                
		private static int utlCadena;
                ItemsClass itemsCl = new ItemsClass();
                static ArrayList<ItemsClass> objClass = new ArrayList();
		
	public ArrayList<ItemsClass> getListObjClass(String rutaArchivo) {
		
		try {

			FileReader archivo;
			BufferedReader lector;

			try {
				String nameTxtFile = rutaArchivo;
				archivo = new FileReader(nameTxtFile);
				if (archivo.ready()) {
					lector = new BufferedReader(archivo);
					String cadena;
					
					while ((cadena = lector.readLine()) != null) {

						String[] indice = cadena.split("[{]");

						for (int i = 0; i < indice.length; i++) {

							if (!indice[i].toString().equals("") && !indice[i].toString().equals(" ")
									&& !indice[i].toString().contains("\\n}\\r")) {

								String cadena3 = cadena.replaceAll("}", "");
								cadena3 = cadena3.trim();

								int indiceLength = indice.length;
								String cadenaRecorrida = indice[i].toString();
                                                                    
                                                                    ItemsClass itemsClass = imprimeCadena(cadena3, indiceLength, cadenaRecorrida);
                                                                
                                                                    boolean exist  = ExisteItemsClass(itemsClass);
                                                                    if(exist){
                                                                        
                                                                    }else{
                                                                        objClass.add(imprimeCadena(cadena3, indiceLength, cadenaRecorrida));
                                                                    }
                                                                    
                                                                
								
							}

						}

					}
				} else {
					System.out.println("El archivo no esta listo para ser leido...");
				}

			} catch (FileNotFoundException e) {
				e.getMessage();
			} catch (IOException e) {
				e.getMessage();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
                
                return objClass;

	}
        
        private boolean ExisteItemsClass(ItemsClass itemsClass)
        {
           boolean existe = false;

           for (int i=0; i<objClass.size();i++) {
               if(objClass.get(i).getClAtributo().equals(itemsClass.getClAtributo()) && 
                  objClass.get(i).getClConstructor().equals(itemsClass.getClConstructor()) &&
                  objClass.get(i).getClDescription().equals(itemsClass.getClDescription()) &&
                  objClass.get(i).getClMParams().equals(itemsClass.getClMParams()) &&
                  objClass.get(i).getClMetodo().equals(itemsClass.getClMetodo()) &&
                  objClass.get(i).getClName().equals(itemsClass.getClName()) &&
                  objClass.get(i).getClScope().equals(itemsClass.getClScope()) &&
                  objClass.get(i).getClSignature().equals(itemsClass.getClSignature()) &&
                  objClass.get(i).getClVari().equals(itemsClass.getClVari())){
                  existe = true; 
               }
            }

           return existe;
        }

	private ItemsClass imprimeCadena(String cadena3, int indiceLength, String par) {
		
		
		
		if (!cadena3.equals("") && indiceLength >= 0) {
			if (par.contains("class")) {

				String cadena4 = cadena3.replaceAll("[{]", "");
				String cadena5 = cadena4.replaceAll("[;]", "");
				String cadena6 = cadena5.trim();
				String[] newStr = cadena6.split("\\s+");

				for (int y = 0; y < newStr.length; y++) {
					LecturaArchivoSCHMService.utlCadena = y;
				}

				this.clName =  newStr[LecturaArchivoSCHMService.utlCadena];
				this.clScope = newStr[0];
				this.clConstructor = newStr[LecturaArchivoSCHMService.utlCadena];
			}

			if (((!par.contains("class") && par.length() >= 2 && (par.contains("(")))
					|| (par.contains(";") && par.length() >= 2))
					&& (par.contains("String") || par.contains("boolean") || par.contains("byte")
							|| par.contains("long") || par.contains("float") || par.contains("chart")
							|| par.contains("short") || par.contains("ArrayList") || par.contains("Vector")
							|| par.contains("Byte") || par.contains("Short") || par.contains("Integer")
							|| par.contains("Long") || par.contains("Float") || par.contains("Double")
							|| par.contains("Character") || par.contains("Boolean") || par.contains("void")
							|| par.contains("int"))) {
				String cadena4 = cadena3.replaceAll("[{]", "");
				String vari = "";
				if (cadena4.contains(";")) {
					vari = "A";
				}
				if (cadena4.contains("(")) {
					vari = "M";
				}
				String cadena5 = cadena4.replaceAll("[;]", "");
				String cadena6 = cadena5.trim();
				String[] newStr = cadena6.split("\\s+");

				for (int y = 0; y < newStr.length; y++) {
					LecturaArchivoSCHMService.utlCadena = y;
				}

				if ("A".equals(vari)) {    
                                    
					this.clAtributo=newStr[2];
                                        this.clVari=vari;
                                        this.clScope=newStr[0];
                                        this.clSignature=newStr[1];
                                        this.clMetodo="";
                                        this.clName="";
                                        this.clDescription="";   
                                        this.clConstructor="";
                                        this.clMParams="";
                                        
				} else {

					int iAbrPar2 = cadena6.indexOf('(');
					int iCerrPar = cadena6.indexOf(')');
					int iAbrPar = newStr[2].indexOf('(');
					int iCerrPar2 = cadena6.indexOf('(');
					String paramMetName = cadena6.substring(iAbrPar2, iCerrPar + 1);
                                        
					this.clMetodo=String.format("%." + iAbrPar + "s", newStr[2]);
                                        this.clVari=vari;
                                        this.clScope=newStr[0];
                                        this.clSignature=newStr[1];
                                        this.clMParams=paramMetName;
                                        this.clName="";
                                        this.clDescription="";   
                                        this.clConstructor="";
                                        this.clAtributo="";
				}
			}

		}
                
                this.itemsCl = new ItemsClass();
                this.itemsCl.setClMetodo(this.clMetodo);
                this.itemsCl.setClName(this.clName);
                this.itemsCl.setClVari(this.clVari);
                this.itemsCl.setClScope(this.clScope);
                this.itemsCl.setClSignature(this.clSignature);
                this.itemsCl.setClDescription(this.clDescription);   
                this.itemsCl.setClConstructor(this.clConstructor);
                this.itemsCl.setClAtributo(this.clAtributo);
                this.itemsCl.setClMParams(this.clMParams);
		
                return itemsCl;		
		
	}

}
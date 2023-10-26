package eventos.modelo.javabean;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
/* Constructor sin argumento */
@AllArgsConstructor
/* Contrusctor con todo */
@Data
/* nos vamos al disco c > usuarios > laura > .m2 > repository > org > proyectolombok y ejecutamos el jar*/
public class Tipo  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idTipo;
	private String nombre;
	
}
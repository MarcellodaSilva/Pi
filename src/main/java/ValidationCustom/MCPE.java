package ValidationCustom;

import javax.validation.Payload;

public @interface MCPE {
	
	String message() default "CEP inválido";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
}

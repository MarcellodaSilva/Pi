package ValidationCustom;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CEPValidator implements ConstraintValidator<MCPE, String> {

	private Pattern pattern = Pattern.compile("[0-9]{5}-[0-9]{3}");
	
	public void initialize(MCPE constraintAnnotation) {
		
	}
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Matcher m = pattern.matcher(value);
		return m.matches();
	}

}

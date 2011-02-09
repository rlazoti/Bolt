package br.com.boltframework.converter;


import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.NumberConverter;

public class BigDecimalConverter extends NumberConverter implements Converter {

	public BigDecimalConverter(boolean allowDecimals) {
		super(allowDecimals);
	}

	@Override
	protected Class<?> getDefaultType() {
		return BigDecimal.class;
	}

	@Override
	public Object convert(@SuppressWarnings("rawtypes") Class clazz, Object value) {
		if (clazz == getDefaultType()) {
			try {
				return new BigDecimal(NumberFormat.getInstance(new Locale("pt", "br")).parse((String) value).doubleValue());
			} catch (ParseException e) {
				return super.convert(clazz, value);
			}
		} else
			return super.convert(clazz, value);
	}
}

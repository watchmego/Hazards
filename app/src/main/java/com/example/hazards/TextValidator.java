package com.example.hazards;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import java.util.regex.Pattern;

public abstract class TextValidator implements TextWatcher {

/*
    MainActivity mEmailAddressField;

    public TextValidator(TextView mEmailAddressField) {

    }

    public abstract void validate(TextView textView, String text);

    @Override
    final public void afterTextChanged(Editable s) {
        String text = mEmailAddressField.getText().toString();
        validate(TextView, text);
    }

    @Override
    final public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* Don't care  }

    @Override
    final public void onTextChanged(CharSequence s, int start, int before, int count) { /* Don't care  }

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public TextValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
        */
    }
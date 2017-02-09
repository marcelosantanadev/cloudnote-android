package marcelosantana.me.cloudnote.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by Marcelo Santana on 12/01/2016.
 */
public abstract class Mask {

    public static String unmask(String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "")
                .replaceAll("[/]", "").replaceAll("[(]", "")
                .replaceAll("[)]", "").replaceAll("[ ]", "");
    }

    public static String mask(final String mask, String string){
        String result = "";

        for(int i = 0, j = 0; i < mask.length() && string.length() > j; i++){
            if(mask.charAt(i) == '#'){
                result += string.charAt(j);
                j++;
            }
            else {
                result += mask.charAt(i);
            }
        }

        return result;
    }

    public static TextWatcher insert(final String mask, final EditText ediTxt) {
        return new TextWatcher() {
            String old = "";
            int positionDelete = 0;

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = unmask(s.toString());
                String result = "";

                if(str.length() < 1) return;
                if(s.toString().equals(old)) return;

                if(before == 1 && old.length() > s.toString().length()){
                    positionDelete = start;

                    if(start > 0 && unmask(new String(old.charAt(start - 1) + "")).equals("")){
                        positionDelete++;
                    }
                }

                boolean exit = false;
                int j = 0;

                for(int i = 0; i < mask.length() && !exit; i++){
                    if(unmask(new String(mask.charAt(i) + "")).equals("")){
                        result += mask.charAt(i);
                        continue;
                    }

                    for(; j < str.length() ;){
                        result += str.charAt(j);

                        if(j + 1 == str.length()) exit = true;

                        j++;
                        break;
                    }
                }

                old = result;
                ediTxt.setText(result);

                if(positionDelete > 0 && ediTxt.getText().toString().length() >= positionDelete){
                    ediTxt.setSelection(positionDelete);
                    positionDelete = 0;
                }
                else {
                    ediTxt.setSelection(result.length());
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void afterTextChanged(Editable s) {}
        };
    }
}
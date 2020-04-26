package challenge;

public class CriptografiaCesariana implements Criptografia {

    private final int SALT = 3;
    private final String ABC = "abcdefghijklmnopqrstuvwxyz";

    @Override
    public String criptografar(String text) {
        if (text.isEmpty()) {
            throw new IllegalArgumentException();
        }

        text = text.toLowerCase();
        String message = "";

        for (int i = 0; i < text.length(); i++) {
            String letter = Character.toString(text.charAt(i));

            if (ABC.contains(letter)) {
                int position = ABC.indexOf(letter);
                int index;

                if (position + SALT > ABC.length()) {
                    index = position - ABC.length() + SALT;
                } else {
                    index = position + SALT;
                }
                message += Character.toString(ABC.charAt(index));
            } else {
                message += letter;
            }
        }
        return message;
    }

    @Override
    public String descriptografar(String text) {
        if (text.isEmpty()) {
            throw new IllegalArgumentException();
        }

        text = text.toLowerCase();
        String message = "";

        for (int i = 0; i < text.length(); i++) {
            String letter = Character.toString(text.charAt(i));

            if (ABC.contains(letter)) {
                int position = ABC.indexOf(letter);
                int index;

                if (position - SALT < 0) {
                    index = position + ABC.length() - SALT;
                } else {
                    index = position - SALT;
                }
                message += Character.toString(ABC.charAt(index));
            } else {
                message += letter;
            }
        }

        return message;
    }
}

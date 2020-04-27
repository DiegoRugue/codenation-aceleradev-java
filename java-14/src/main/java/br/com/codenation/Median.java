package br.com.codenation;

class Median {
    private int element;
    private int quantity;

    public Median(int element, int quantity) {
        this.element = element;
        this.quantity = quantity;
    }

    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity() {
        this.setQuantity(this.getQuantity() + 1);
    }
}

package id.co.hci.pretest.tester;

import java.lang.reflect.Method;

public class GetterSetterPair {
    /**
     * The get method.
     */
    private Method getter;

    /**
     * The set method.
     */
    private Method setter;

    /**
     * Returns if this has a getter and setter method set.
     *
     * @return if this has a getter and setter method set
     */
    public boolean hasGetterAndSetter() {
        return this.getter != null && this.setter != null;
    }

    /**
     * Returns the get method.
     *
     * @return the get method
     */
    public Method getGetter() {
        return getter;
    }

    /**
     * Sets the get method.
     *
     * @param getter the set method
     */
    public void setGetter(Method getter) {
        this.getter = getter;
    }

    /**
     * Returns the set method.
     *
     * @return the set method
     */
    public Method getSetter() {
        return setter;
    }

    /**
     * Sets the set method.
     *
     * @param setter the set method
     */
    public void setSetter(Method setter) {
        this.setter = setter;
    }

}

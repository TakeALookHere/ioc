package com.miskevich.ioc.data;

import java.util.List;

public class BeanDefinition {
    private String id;
    private String className;
    private List<BeanProperty> beanProperties;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<BeanProperty> getBeanProperties() {
        return beanProperties;
    }

    public void setBeanProperties(List<BeanProperty> beanProperties) {
        this.beanProperties = beanProperties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BeanDefinition that = (BeanDefinition) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (className != null ? !className.equals(that.className) : that.className != null) return false;
        return beanProperties != null ? beanProperties.equals(that.beanProperties) : that.beanProperties == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + (beanProperties != null ? beanProperties.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BeanDefinition{" +
                "id='" + id + '\'' +
                ", className='" + className + '\'' +
                ", beanProperties=" + beanProperties +
                '}';
    }
}

package com.grupo_5.pub.Model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "mesas")
public class Mesas {

    @JacksonXmlProperty(localName = "mesa")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Mesa> mesas;

    public Mesas() {}

    public Mesas(List<Mesa> mesas) {
        this.mesas = mesas;
    }

    public List<Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(List<Mesa> mesas) {
        this.mesas = mesas;
    }
}


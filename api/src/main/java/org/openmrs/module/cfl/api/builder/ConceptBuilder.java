package org.openmrs.module.cfl.api.builder;

import org.openmrs.Concept;
import org.openmrs.ConceptClass;
import org.openmrs.ConceptDatatype;
import org.openmrs.ConceptName;

public class ConceptBuilder {

    private ConceptDatatype conceptDatatype;

    private ConceptClass conceptClass;

    private boolean isSet;

    private ConceptName conceptFullyName;

    public Concept build() {
        Concept concept = new Concept();
        concept.setDatatype(conceptDatatype);
        concept.setConceptClass(conceptClass);
        concept.setSet(isSet);
        concept.setFullySpecifiedName(conceptFullyName);
        return concept;
    }

    public ConceptBuilder withConceptDatatype(ConceptDatatype conceptDatatype) {
        this.conceptDatatype = conceptDatatype;
        return this;
    }

    public ConceptBuilder withConceptClass(ConceptClass conceptClass) {
        this.conceptClass = conceptClass;
        return this;
    }

    public ConceptBuilder withIsSet(boolean isSet) {
        this.isSet = isSet;
        return this;
    }

    public ConceptBuilder withConceptName(ConceptName conceptName) {
        this.conceptFullyName = conceptName;
        return this;
    }
}

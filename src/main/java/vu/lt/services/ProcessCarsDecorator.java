package vu.lt.services;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class ProcessCarsDecorator implements IProcessCompanyCars {

    @Inject
    @Delegate
    @Any
    IProcessCompanyCars processCompanyCars;

    @Override
    public Integer processCars(Integer companyId) {
        System.out.println("DECORATOR processCars()");
        return processCompanyCars.processCars(companyId);
    }
}

package vu.lt.usecases;

import vu.lt.interceptors.LoggedInvocation;
import vu.lt.services.ProcessCompanyCars;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class UploadCars implements Serializable {
    @Inject
    ProcessCompanyCars processCompanyCars;

    private Map<Integer, CompletableFuture<Integer>> processTasks = new HashMap<>();

    @LoggedInvocation
    public String uploadCars(Integer companyId) {
        processTasks.put(companyId, CompletableFuture.supplyAsync(() -> processCompanyCars.uploadCarsToCloud(companyId)));
        return "companyDetails?faces-redirect=true&companyId=" + companyId;
    }

    public boolean isRunning(Integer companyId) {
        CompletableFuture<Integer> task = processTasks.get(companyId);
        return task != null && !task.isDone();
    }

    public String getUploadStatus(Integer companyId) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> task = processTasks.get(companyId);
        if (task == null)
            return null;

        if (!task.isDone()){
            return "Task in process";
        }

        return "Processed " + task.get() + " cars";
    }
}

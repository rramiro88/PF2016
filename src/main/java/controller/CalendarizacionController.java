/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import calendarizacion.TareaCalendarizada;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import logica.LogicaAdministracion;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.TriggerKey.triggerKey;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author alumno
 */
@Named
@ApplicationScoped
public class CalendarizacionController {
    
    @Inject
    LogicaAdministracion logicaAdministacion;
    
    private boolean schedulerActivo = false;

    
    public CalendarizacionController(){
        try{
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            if(scheduler.isStarted())
                schedulerActivo=true;
        }catch(SchedulerException se){
            se.printStackTrace();
        }
    }
    public void iniciarScheduler() {

        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // and start it off
            scheduler.start();
            schedulerActivo = true;

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

    public void pararScheduler() {

        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            scheduler.shutdown();
            schedulerActivo = false;

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

    public void agregarTrabajo() {

        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // define the job and tie it to our HelloJob class
            JobDetail job = newJob(TareaCalendarizada.class)
                    .withIdentity("job1", "group1")
                    .build();

            // Trigger the job to run now, and then repeat every 40 seconds
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(cronSchedule("0 0/1 * 1/1 * ? *"))
                    //                    .withSchedule(simpleSchedule()
                    //                            .withIntervalInSeconds(10)
                    //                            .repeatForever())
                    .build();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException se) {

        }

    }
    
     public void quitarTrabajo() {

        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            
            scheduler.unscheduleJob(triggerKey("trigger1", "group1"));
            
            
        } catch (SchedulerException se) {

        }

    }

    public boolean isSchedulerActivo() {
        return schedulerActivo;
    }

    public void setSchedulerActivo(boolean schedulerActivo) {
        this.schedulerActivo = schedulerActivo;
    }

    
    public void avanzarUnDia(){
        logicaAdministacion.avanzarUnDia();
    }
    
}

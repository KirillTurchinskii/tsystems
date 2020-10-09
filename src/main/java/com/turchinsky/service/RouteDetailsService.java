package com.turchinsky.service;

import com.turchinsky.dao.RouteDetailsDao;
import com.turchinsky.entities.RouteDetailsEntity;
import com.turchinsky.entities.RouteDetailsEntityPK;
import com.turchinsky.entities.RouteEntity;
import com.turchinsky.entities.StationEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteDetailsService implements ExtendedCRUDService<RouteDetailsEntity, RouteDetailsEntityPK> {

    private final RouteDetailsDao routeDetailsDao;

    private final RouteService routeService;

    private final StationService stationService;

    public RouteDetailsService(RouteDetailsDao routeDetailsDao, RouteService routeService,
                               StationService stationService) {
        this.routeDetailsDao = routeDetailsDao;
        this.routeService = routeService;
        this.stationService = stationService;
    }

    public boolean checkIdentity(RouteDetailsEntity routeDetailsEntity) {
        RouteDetailsEntityPK pk = routeDetailsEntity.createPK();
        List<RouteDetailsEntity> listByPK = routeDetailsDao.getListByPK(pk);
        return listByPK.size() == 0 || listByPK.size() == 1 && checkequality(routeDetailsEntity, pk);
    }

    public boolean checkequality(RouteDetailsEntity routeDetailsEntity, RouteDetailsEntityPK routeDetailsEntityPK) {
        return routeDetailsEntity.getRouteId() == routeDetailsEntityPK.getRouteId() &&
                routeDetailsEntity.getStationId() == routeDetailsEntityPK.getStationId() &&
                routeDetailsEntity.getStationOrder() == routeDetailsEntityPK.getStationOrder();
    }

    public List<RouteEntity> getAllRoutes() {
        return routeService.getAll();
    }

    public List<StationEntity> getAllStations() {
        return stationService.getAll();
    }

//    public int getNextStationOrder(RouteDetailsEntity routeDetailsEntity){
//       return routeDetailsDao.getNextStationOrder(routeDetailsEntity.getRouteId());
//    }

//    public String getRouteName(){
//
//    }

//    public String getStationName(){
//
//    }

    public List<RouteDetailsEntity> getRouteDetailsByRouteId(int id) {
        return routeDetailsDao.getRouteDetailsByRouteId(id);
    }

    public void setUp(RouteDetailsEntity routeDetailsEntity) {
        addRefs(routeDetailsEntity);
        setStationOrder(routeDetailsEntity);
    }

    public List<StationEntity> getFreeStations(int routeId) {
        List<StationEntity> allStations = getAllStations();
        List<Integer> usedStations = getUsedStations(routeId);
        List<StationEntity> freeStations = new ArrayList<>();
        for (StationEntity stationEntity :
                allStations) {
            if (!usedStations.contains(stationEntity.getId())) {
                freeStations.add(stationEntity);
            }
        }
        return freeStations;
    }

    public void setStationOrder(RouteDetailsEntity routeDetailsEntity) {
        List<Integer> nextStationOrderList = routeDetailsDao.getNextStationOrder(routeDetailsEntity.getRouteId());
        System.out.println("Next station Order Size" + nextStationOrderList.size());
//        if (nextStationOrderList.size()==0){
//            routeDetailsEntity.setStationOrder(1);
//        }
        routeDetailsEntity.setStationOrder(nextStationOrderList.size() + 1);
    }

    public void addRefs(RouteDetailsEntity routeDetailsEntity) {
        routeDetailsEntity.setRefRouteEntity(getRouteById(routeDetailsEntity.getRouteId())); //Если что проблема тут
        routeDetailsEntity
                .setRefStationEntity(getStationById(routeDetailsEntity.getStationId())); //Если что проблема тут
    }

    public RouteEntity getRouteById(int id) {
        return routeService.get(id);
    }

    public StationEntity getStationById(int id) {
        return stationService.get(id);
    }

    public int getMaxKm(int routeId) {
        return routeDetailsDao.getMaxKm(routeId);
    }

    public boolean validateKm(int routeId, int km) {
        getRouteById(routeId);
        int maxKm = -1;
        if (getUsedStations(routeId).size() > 0) {
            maxKm = getMaxKm(routeId);
        } else {
            return km == 0;
        }
        return maxKm < km;
    }

    public boolean validateKm(int routeId, int stationOrder, int km) {
        if (stationOrder == 1) {
            return km == 0;
        } else {
            boolean hasNextStation = getUsedStations(routeId).size() >= stationOrder + 1;
            int prevStationKm = getKmByStationOrder(routeId, stationOrder - 1);
            return hasNextStation ? km > prevStationKm && km < getKmByStationOrder(routeId,
                                                                                   stationOrder + 1) : km > prevStationKm;
        }

    }

    public void deleteThis(RouteDetailsEntity routeDetailsEntity) {
        routeDetailsDao.delete(routeDetailsEntity);
    }

    private int getKmByStationOrder(int routeId, int stationOrder) {
        return routeDetailsDao.getKmByStationOrder(routeId, stationOrder);
    }

    private List<Integer> getUsedStations(int routeId) {
        return routeDetailsDao.getUsedStations(routeId);
    }

    private void updateNextRouteDetailsAndDelete(RouteDetailsEntity routeDetailsEntity) {
        if (routeDetailsEntity.getStationOrder() < getUsedStations(routeDetailsEntity.getRouteId()).size()) {
            List<RouteDetailsEntity> nextEntities = getAllRouteDetailsAfterThis(routeDetailsEntity);
            int decreaseKm = Math.abs(getKmByStationOrder(routeDetailsEntity.getRouteId(),
                                                          routeDetailsEntity.getStationOrder() + 1) - routeDetailsEntity
                    .getKm());
            deleteThis(routeDetailsEntity);
            for (RouteDetailsEntity entity :
                    nextEntities) {
                entity.setKm(entity.getKm() - decreaseKm);
                save(entity);
                updateStationOrder(entity, entity.getStationOrder() - 1);
                save(entity);
            }
        } else {
            deleteThis(routeDetailsEntity);
        }
    }

    private void updateStationOrder(RouteDetailsEntity entity, int newOrder) {
        routeDetailsDao.updateStationOrder(entity, newOrder);

    }

    private List<RouteDetailsEntity> getAllRouteDetailsAfterThis(RouteDetailsEntity routeDetailsEntity) {
        return routeDetailsDao.getAllAfter(routeDetailsEntity);
    }

    @Override
    public RouteDetailsEntity get(RouteDetailsEntityPK routeDetailsEntityPK) {
        return routeDetailsDao.get(routeDetailsEntityPK);
    }

    @Override
    public List<RouteDetailsEntity> getAll() {
        return routeDetailsDao.getAll();
    }

    @Override
    public RouteDetailsEntity save(RouteDetailsEntity routeDetailsEntity) {
//        addRefs(routeDetailsEntity);
        return routeDetailsDao.save(routeDetailsEntity);
    }

    @Override
    public void delete(RouteDetailsEntity routeDetailsEntity) {
        RouteDetailsEntityPK routeDetailsEntityPK = new RouteDetailsEntityPK(routeDetailsEntity.getRouteId(),
                                                                             routeDetailsEntity.getStationId(),
                                                                             routeDetailsEntity.getStationOrder());
        RouteDetailsEntity managedEntity = get(routeDetailsEntityPK);
        updateNextRouteDetailsAndDelete(managedEntity);
    }


}

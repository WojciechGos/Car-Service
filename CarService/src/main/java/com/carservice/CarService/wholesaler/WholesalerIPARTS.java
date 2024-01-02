package com.carservice.CarService.wholesaler;

import com.carservice.CarService.exception.ResourceNotFoundException;
import com.carservice.CarService.requestItem.RequestItemDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WholesalerIPARTS {
    private final List<RequestItemDTO> requestItemDTOList;

    public WholesalerIPARTS() {
        requestItemDTOList = new ArrayList<>();

        requestItemDTOList.add(new RequestItemDTO(123L, "KNECHT", "IPART", "Producent\tKNECHT\n" +
                "Indeks\tOX554D1\n" +
                "Kod EAN\t4009026728598\n" +
                "Typ filtra\tWkład filtra\n" +
                "Wysokość [mm]\t117\n" +
                "Wysokość 2 [mm]\t112,4", "Filtr oleju", new BigDecimal("78.99"), 15));

        requestItemDTOList.add(new RequestItemDTO(101L, "BOSCH", "IPART", "Producent\tBOSCH\n" +
                "Indeks\tF00E164579\n" +
                "Kod EAN\t3165143857524\n" +
                "Typ filtra\tWkład filtra powietrza\n" +
                "Wysokość [mm]\t220\n" +
                "Szerokość [mm]\t203", "Filtr powietrza", new BigDecimal("45.99"), 20));

        requestItemDTOList.add(new RequestItemDTO(102L, "DENSO", "IPART", "Producent\tDENSO\n" +
                "Indeks\tDCP17003\n" +
                "Kod EAN\t4011648957522\n" +
                "Typ filtra\tKabina filtra\n" +
                "Długość [mm]\t235\n" +
                "Szerokość [mm]\t215", "Filtr kabinowy", new BigDecimal("32.50"), 10));

        requestItemDTOList.add(new RequestItemDTO(103L, "MANN-FILTER", "IPART", "Producent\tMANN-FILTER\n" +
                "Indeks\tC25114/2\n" +
                "Kod EAN\t4011558017259\n" +
                "Typ filtra\tFiltr paliwa\n" +
                "Średnica zewnętrzna [mm]\t76\n" +
                "Średnica wewnętrzna [mm]\t55", "Filtr paliwa", new BigDecimal("18.75"), 30));

        requestItemDTOList.add(new RequestItemDTO(201L, "LUK", "IPART", "Producent\tLUK\n" +
                "Indeks\t600 0167 00\n" +
                "Kod EAN\t4005108588643\n" +
                "Średnica [mm]\t240\n" +
                "Liczba zębów\t23", "Sprzęgło", new BigDecimal("289.99"), 5));

        requestItemDTOList.add(new RequestItemDTO(202L, "VALEO", "IPART", "Producent\tVALEO\n" +
                "Indeks\t826364\n" +
                "Kod EAN\t3276428263647\n" +
                "Średnica [mm]\t200\n" +
                "Liczba zębów\t21", "Sprzęgło", new BigDecimal("239.50"), 8));

        requestItemDTOList.add(new RequestItemDTO(203L, "SACHS", "IPART", "Producent\tSACHS\n" +
                "Indeks\t3000 951 477\n" +
                "Kod EAN\t4013872905499\n" +
                "Średnica [mm]\t215\n" +
                "Liczba zębów\t18", "Sprzęgło", new BigDecimal("199.99"), 10));

        requestItemDTOList.add(new RequestItemDTO(204L, "EXEDY", "IPART", "Producent\tEXEDY\n" +
                "Indeks\tMBK1006\n" +
                "Kod EAN\t9319427752783\n" +
                "Średnica [mm]\t250\n" +
                "Liczba zębów\t24", "Sprzęgło", new BigDecimal("319.75"), 6));

        requestItemDTOList.add(new RequestItemDTO(205L, "AISIN", "IPART", "Producent\tAISIN\n" +
                "Indeks\tKT-004\n" +
                "Kod EAN\t4954514999027\n" +
                "Średnica [mm]\t228\n" +
                "Liczba zębów\t21", "Sprzęgło", new BigDecimal("279.00"), 7));

        requestItemDTOList.add(new RequestItemDTO(301L, "BREMBO", "IPART", "Producent\tBREMBO\n" +
                "Indeks\tP59085\n" +
                "Kod EAN\t8020584059883\n" +
                "Średnica tarczy [mm]\t300\n" +
                "Grubość tarczy [mm]\t25", "Tarcze hamulcowe", new BigDecimal("149.99"), 8));

        requestItemDTOList.add(new RequestItemDTO(302L, "ATE", "IPART", "Producent\tATE\n" +
                "Indeks\t24.0122-0227.1\n" +
                "Kod EAN\t4006633415573\n" +
                "Średnica tarczy [mm]\t320\n" +
                "Grubość tarczy [mm]\t22", "Tarcze hamulcowe", new BigDecimal("169.50"), 10));

        requestItemDTOList.add(new RequestItemDTO(303L, "EBC Brakes", "IPART", "Producent\tEBC Brakes\n" +
                "Indeks\tGD1446\n" +
                "Kod EAN\t5039221014468\n" +
                "Średnica tarczy [mm]\t260\n" +
                "Grubość tarczy [mm]\t12", "Tarcze hamulcowe", new BigDecimal("99.99"), 12));

        requestItemDTOList.add(new RequestItemDTO(304L, "TRW", "IPART", "Producent\tTRW\n" +
                "Indeks\tDF4313S\n" +
                "Kod EAN\t3322937913324\n" +
                "Średnica tarczy [mm]\t280\n" +
                "Grubość tarczy [mm]\t22", "Tarcze hamulcowe", new BigDecimal("119.75"), 15));

        requestItemDTOList.add(new RequestItemDTO(305L, "BOSCH", "IPART", "Producent\tBOSCH\n" +
                "Indeks\t0 986 479 107\n" +
                "Kod EAN\t4047024797262\n" +
                "Klocki hamulcowe\n" +
                "Wysokość [mm]\t49,9\n" +
                "Szerokość [mm]\t116,2", "Klocki hamulcowe", new BigDecimal("49.50"), 20));

        requestItemDTOList.add(new RequestItemDTO(306L, "Ferodo", "IPART", "Producent\tFerodo\n" +
                "Indeks\tFDB1785\n" +
                "Kod EAN\t4044197698525\n" +
                "Klocki hamulcowe\n" +
                "Wysokość [mm]\t57,2\n" +
                "Szerokość [mm]\t109,5", "Klocki hamulcowe", new BigDecimal("39.99"), 25));

        requestItemDTOList.add(new RequestItemDTO(401L, "BEHR", "IPART", "Producent\tBEHR\n" +
                "Indeks\t8MK 376 715-741\n" +
                "Kod EAN\t4045621456321\n" +
                "Materiał\tAluminium\n" +
                "Wysokość [mm]\t650\n" +
                "Szerokość [mm]\t400", "Chłodnica", new BigDecimal("189.99"), 5));

        requestItemDTOList.add(new RequestItemDTO(402L, "NISSENS", "IPART", "Producent\tNISSENS\n" +
                "Indeks\t64668A\n" +
                "Kod EAN\t5707286208036\n" +
                "Materiał\tAluminium\n" +
                "Wysokość [mm]\t500\n" +
                "Szerokość [mm]\t450", "Chłodnica", new BigDecimal("169.50"), 8));

        requestItemDTOList.add(new RequestItemDTO(403L, "VALEO", "IPART", "Producent\tVALEO\n" +
                "Indeks\t732523\n" +
                "Kod EAN\t3276427325235\n" +
                "Materiał\tPlastik/Aluminium\n" +
                "Wysokość [mm]\t620\n" +
                "Szerokość [mm]\t420", "Chłodnica", new BigDecimal("219.99"), 10));

        requestItemDTOList.add(new RequestItemDTO(404L, "HELLA", "IPART", "Producent\tHELLA\n" +
                "Indeks\t8MK 376 721-741\n" +
                "Kod EAN\t4045621475742\n" +
                "Materiał\tAluminium\n" +
                "Wysokość [mm]\t700\n" +
                "Szerokość [mm]\t450", "Chłodnica", new BigDecimal("199.75"), 6));

        requestItemDTOList.add(new RequestItemDTO(405L, "NRF", "IPART", "Producent\tNRF\n" +
                "Indeks\t53622\n" +
                "Kod EAN\t8718042053137\n" +
                "Materiał\tAluminium\n" +
                "Wysokość [mm]\t550\n" +
                "Szerokość [mm]\t400", "Chłodnica", new BigDecimal("179.00"), 7));
    }
    // this method simulates the work done on the IPART server side
    public RequestItemDTO findItemById(Long targetId) {
        for (RequestItemDTO item : requestItemDTOList) {
            if (item.id().equals(targetId)) {
                return item;
            }
        }
        throw new ResourceNotFoundException("Order Item with id [%s] not found".formatted(targetId));
    }

    public RequestItemDTO post(Long id) {
        return findItemById(id);
    }

    public List<RequestItemDTO> get() {
        return requestItemDTOList;
    }

    public void put(Long targetId, int quantity) {
        requestItemDTOList.stream()
                .filter(item -> Objects.equals(item.id(), targetId))
                .findFirst()
                .ifPresent(item -> {
                    RequestItemDTO updatedItem = new RequestItemDTO(
                            item.id(),
                            item.producerName(),
                            item.wholesaler(),
                            item.parameters(),
                            item.itemName(),
                            item.price(),
                            item.quantity() - quantity
                    );

                    requestItemDTOList.set(requestItemDTOList.indexOf(item), updatedItem);
                });
    }
}

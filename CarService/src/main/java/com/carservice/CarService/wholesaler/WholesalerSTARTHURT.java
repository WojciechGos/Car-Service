package com.carservice.CarService.wholesaler;

import com.carservice.CarService.exception.ResourceNotFoundException;
import com.carservice.CarService.requestItem.RequestItemDTO;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WholesalerSTARTHURT {
    private final List<RequestItemDTO> requestItemDTOList;

    public WholesalerSTARTHURT() {
        requestItemDTOList = loadItemsFromXml();
    }

    private List<RequestItemDTO> loadItemsFromXml() {
        List<RequestItemDTO> items = new ArrayList<>();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File xmlFile = new File(Objects.requireNonNull(classLoader.getResource("STARTHURT.xml")).getFile());

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("item");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    RequestItemDTO item = new RequestItemDTO(
                            Long.parseLong(getElementValue(element, "id")),
                            getElementValue(element, "producerName"),
                            getElementValue(element, "wholesaler"),
                            getElementValue(element, "parameters"),
                            getElementValue(element, "itemName"),
                            new BigDecimal(getElementValue(element, "price")),
                            Integer.parseInt(getElementValue(element, "quantity"))
                    );
                    items.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    private static String getElementValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

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

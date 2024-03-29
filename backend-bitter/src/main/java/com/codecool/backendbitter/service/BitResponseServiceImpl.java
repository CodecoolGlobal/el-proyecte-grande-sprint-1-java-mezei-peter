package com.codecool.backendbitter.service;

import com.codecool.backendbitter.controller.dto.BitResponseDTO;
import com.codecool.backendbitter.controller.dto.NewBitResponseDTO;
import com.codecool.backendbitter.controller.dto.UpdateBitResponseDTO;
import com.codecool.backendbitter.model.BitResponse;
import com.codecool.backendbitter.repository.BitResponseRepository;
import com.codecool.backendbitter.utility.BitResponseMapper;
import com.codecool.backendbitter.utility.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BitResponseServiceImpl implements BitResponseService{

    private final BitResponseMapper bitResponseMapper;
    private final BitResponseRepository bitResponseRepository;

    @Autowired
    public BitResponseServiceImpl(BitResponseMapper bitResponseMapper,
                                  BitResponseRepository bitResponseRepository) {
        this.bitResponseMapper = bitResponseMapper;
        this.bitResponseRepository = bitResponseRepository;
    }

    @Override
    public BitResponseDTO addBitResponse(NewBitResponseDTO newBitResponseDTO) {
        BitResponse bitResponse =
                bitResponseMapper.newBitResponseDTOToBitResponse(newBitResponseDTO);
        BitResponse savedBitResponse = bitResponseRepository.save(bitResponse);

        return bitResponseMapper.bitResponseToBitResponseDTO(savedBitResponse);
    }

    @Override
    public List<BitResponseDTO> findBitResponsesByBitId(String bitId) {
        UUID convertedBitId = UUID.fromString(bitId);
        List<BitResponse> bitResponseList =
                bitResponseRepository.findAllByBit_BitId(convertedBitId);
        List<BitResponseDTO> bitResponseDTOS =
                bitResponseList.stream().map(bitResponseMapper::bitResponseToBitResponseDTO).toList();
        return bitResponseDTOS;
    }

    public void deleteBitResponseByBitResponseId(String bitResponseId) throws ResourceNotFoundException {
        int affectedRows = bitResponseRepository.deleteByBitResponseId(UUID.fromString(bitResponseId));

        if (affectedRows == 0) throw new ResourceNotFoundException("Bit Response with id: " + bitResponseId + " not found.");
    }

    @Override
    public void updateBitResponseByBitResponseId(UpdateBitResponseDTO updateBitResponseDTO) throws ResourceNotFoundException {
        try {
            BitResponse bitResponse =
                    bitResponseMapper.updateBitResponseDTOToBitResponse(updateBitResponseDTO);

            bitResponseRepository.save(bitResponse);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new ResourceNotFoundException("Resource with id: " + updateBitResponseDTO.responseId() + " not found");
        }
    }
}

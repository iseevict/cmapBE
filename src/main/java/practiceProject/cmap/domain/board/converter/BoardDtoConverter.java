package practiceProject.cmap.domain.board.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.web.multipart.MultipartFile;
import practiceProject.cmap.domain.board.dto.BoardParameterDTO;
import practiceProject.cmap.domain.board.dto.BoardRequestDTO;
import practiceProject.cmap.domain.board.dto.BoardResponseDTO;

import java.util.List;

@Mapper
public interface BoardDtoConverter {

    BoardDtoConverter INSTANCE = Mappers.getMapper(BoardDtoConverter.class);

    BoardParameterDTO.BoardWriteParamDto toBoardWriteParamDto(BoardRequestDTO.BoardWriteRequestDto request, List<MultipartFile> boardPictureList);
    BoardParameterDTO.BoardModifyParamDto toBoardModifyParamDto(BoardRequestDTO.BoardModifyRequestDto request, Long boardId);
    BoardParameterDTO.BoardDeleteParamDto toBoardDeleteParamDto(BoardRequestDTO.BoardDeleteRequestDto request, Long boardId);
    BoardParameterDTO.BoardHeartOnParamDto toBoardHeartOnParamDto(BoardRequestDTO.BoardHeartOnRequestDto request, Long boardId);
    BoardParameterDTO.BoardHeartOffParamDto toBoardHeartOffParamDto(Long memberId, Long boardId);
    BoardParameterDTO.BoardListParamDto toBoardListParamDto(Integer page, Integer size);
    BoardParameterDTO.BoardListByTagParamDto toBoardListByTagParamDto(Integer page, Integer size, List<Long> tagList);
    BoardParameterDTO.BoardDataParamDto toBoardDataParamDto(Long boardId);
}

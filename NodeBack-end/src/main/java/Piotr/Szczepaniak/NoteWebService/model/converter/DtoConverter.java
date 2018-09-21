package Piotr.Szczepaniak.NoteWebService.model.converter;

public interface DtoConverter<TEntity, TListDto, TDetailsDto, TCreationDto, TUpdateDto> {
    TEntity creation(TCreationDto creationDto);

    TEntity update(TUpdateDto updateDto);

    TListDto list(TEntity entity);

    TDetailsDto details(TEntity entity);
}

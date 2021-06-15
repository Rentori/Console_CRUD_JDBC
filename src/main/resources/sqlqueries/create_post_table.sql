CREATE TABLE Posts (
                      ID BIGINT AUTO_INCREMENT,
                      Content VARCHAR(255) NOT NULL,
                      Created DATE NOT NULL,
                      Updated DATE NOT NULL,
                      PostStatus varchar(255) not null,
                      WriterId BIGINT,
                      PRIMARY KEY(ID)
)
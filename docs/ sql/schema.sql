---------------------------------Enums--------------------------------------------

CREATE TYPE project_status AS ENUM ('IN_PROGRESS', 'COMPLETED', 'CANCELLED');
CREATE TYPE component_type AS ENUM ('MATERIAL', 'LABOR');

-----------------------------------Client-------------------------------------------
CREATE TABLE Client (
    id UUID PRIMARY KEY,
    fullName VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phoneNumber VARCHAR(20),
    isProfessional BOOLEAN
);

-----------------------------------Project -----------------------------------------

CREATE TABLE Project (
    id UUID PRIMARY KEY,
    project_name VARCHAR(255) NOT NULL,
    profit_margin FLOAT,
    status project_status,
    client_id UUID,
    FOREIGN KEY (client_id) REFERENCES Client(id)
);

-----------------------------------Component -----------------------------------------

CREATE TABLE component (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL CHECK (type IN ('MATERIAL', 'LABOR')),
    vat_rate FLOAT NOT NULL,
    project_id UUID NOT NULL,
    FOREIGN KEY (project_id) REFERENCES project(id)
);


-----------------------------------Material -----------------------------------------

CREATE TABLE material (
    id UUID PRIMARY KEY,
    unit_cost FLOAT NOT NULL,
    quantity INTEGER NOT NULL,
    transport_cost FLOAT NOT NULL,
    quality_coefficient FLOAT NOT NULL,
    FOREIGN KEY (id) REFERENCES component(id)
) inherits;


-----------------------------------WorkForce -----------------------------------------

CREATE TABLE workforce (
    id UUID PRIMARY KEY,
    hourly_rate FLOAT NOT NULL,
    work_hours FLOAT NOT NULL,
    worker_productivity FLOAT NOT NULL,
    FOREIGN KEY (id) REFERENCES component(id)
);


-----------------------------------Estimate -----------------------------------------


CREATE TABLE Estimate (
    id UUID PRIMARY KEY,
    estimatedAmount REAL,
    issueDate DATE,
    validityDate DATE,
    isAccepted BOOLEAN,
    projectId UUID,
    FOREIGN KEY (projectId) REFERENCES Project(id)
);

-------------------------------- update full name in client to be unique --------------------------

ALTER TABLE client ADD CONSTRAINT unique_fullname UNIQUE (fullname);

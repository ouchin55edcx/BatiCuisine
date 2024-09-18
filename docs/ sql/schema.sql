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
    projectName VARCHAR(255) NOT NULL,
    profitMargin FLOAT,
    status project_status,
    clientId UUID,
    FOREIGN KEY (clientId) REFERENCES Client(id)
);

-----------------------------------Component -----------------------------------------

CREATE TABLE Component (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type component_type NOT NULL,
    vatRate FLOAT,
    projectId UUID,
    FOREIGN KEY (projectId) REFERENCES Project(id)
);

-----------------------------------Material -----------------------------------------

CREATE TABLE Material (
    id UUID PRIMARY KEY,
    unitCost REAL,
    quantity INTEGER,
    transportCost REAL,
    qualityCoefficient REAL,
    componentId UUID,
    FOREIGN KEY (componentId) REFERENCES Component(id)
);

-----------------------------------WorkForce -----------------------------------------

CREATE TABLE WorkForce (
    id UUID PRIMARY KEY,
    hourlyRate REAL,
    workHours REAL,
    workerProductivity REAL,
    componentId UUID,
    FOREIGN KEY (componentId) REFERENCES Component(id)
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
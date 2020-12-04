CREATE TABLE team(
    id UUID PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE partner(
    id UUID PRIMARY KEY,
    name TEXT,
    email VARCHAR(255),
    birth_day VARCHAR(10),
    heart_team_id UUID,
    created_at TIMESTAMP WITH TIME ZONE ,
    updated_at TIMESTAMP WITH TIME ZONE,
    FOREIGN KEY(heart_team_id) REFERENCES team(id)
);

CREATE TABLE subscription_campaign(
    team_id UUID,
    campaign_id UUID,
    created_at TIMESTAMP WITH TIME ZONE ,
    updated_at TIMESTAMP WITH TIME ZONE
);
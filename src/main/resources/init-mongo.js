db = db.getSiblingDB('inetpsa');

db.createCollection('ServiceStepAssociations');

db.ServiceStepAssociations.insertMany([
  { "step": "FCL_ELIGIBILITY", "services": ["MOTA_CELL"], "rights": false },
  { "step": "FCL_ASSOCIATION", "services": ["MOTA_CELL"], "rights": false },
  { "step": "TOMTOM_ACTIVATION", "services": ["NAL03", "NAB02"], "rights": false },
  { "step": "COMMODORE_ACTIVATION", "services": ["NAL03", "NAB02"], "rights": false },
  { "step": "IGNITE_ACTIVATION", "services": ["MOTA_CELL"], "rights": false },
  { "step": "CORVET_ACTIVATION", "services": ["NAL03"], "rights": false },
  { "step": "TOMTOM_UPDATE", "services": ["NAL03"], "rights": true },
  { "step": "CVS_ALLOCATION", "services": ["NAL03"], "rights": true },
  { "step": "CVS_DEALLOCATION", "services": ["NAL03"], "rights": true },
  { "step": "CVS_UPDATE", "services": ["NAL03"], "rights": true },
  { "step": "COMMODORE_DEACTIVATION", "services": ["NAL03"], "rights": false },
  { "step": "IGNITE_DEACTIVATION", "services": ["MOTA_CELL"], "rights": false },
  { "step": "CORVET_DEACTIVATION", "services": ["NAL03"], "rights": false },
  { "step": "TOMTOM_DEACTIVATION", "services": ["NAL03"], "rights": false }
]);